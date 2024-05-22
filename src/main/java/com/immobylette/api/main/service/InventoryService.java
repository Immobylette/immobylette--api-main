package com.immobylette.api.main.service;

import com.immobylette.api.main.domain.InventoryStateLabel;
import com.immobylette.api.main.domain.StateTypeEnum;
import com.immobylette.api.main.domain.WallTypeEnum;
import com.immobylette.api.main.dto.*;
import com.immobylette.api.main.entity.*;
import com.immobylette.api.main.exception.*;
import com.immobylette.api.main.mapper.ElementMapper;
import com.immobylette.api.main.mapper.ElementSummaryMapper;
import com.immobylette.api.main.mapper.RoomMapper;
import com.immobylette.api.main.mapper.StepReceivedMapper;
import com.immobylette.api.main.repository.ElementRepository;
import com.immobylette.api.main.repository.PropertyRepository;
import com.immobylette.api.main.repository.RoomRepository;
import com.immobylette.api.main.repository.StepRepository;
import com.immobylette.api.main.entity.enums.InventoryTypeLabel;
import com.immobylette.api.main.repository.InventoryRepository;
import com.immobylette.api.main.repository.InventoryTypeRepository;
import com.immobylette.api.main.repository.LeaseRepository;
import com.immobylette.api.main.repository.ThirdPartyRepository;
import com.immobylette.api.main.resource.FolderResource;
import com.immobylette.api.main.resource.PhotoResource;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    private final InventoryTypeRepository inventoryTypeRepository;
    private final ThirdPartyRepository thirdPartyRepository;
    private final LeaseRepository leaseRepository;
    private final RoomRepository roomRepository;

    private final PropertyRepository propertyRepository;

    private final ElementRepository elementRepository;

    private final StepRepository stepRepository;

    private final RoomMapper roomMapper;

    private final ElementSummaryMapper elementSummaryMapper;

    private final ElementMapper elementMapper;

    private final PhotoResource photoResource;

    private final FolderResource folderResource;

    private final StepReceivedMapper stepMapper;


    public UUID createInventory(UUID propertyId, UUID agentId)  throws PropertyNotAssociatedWithAnyLeaseException, AgentNotFoundException {
        Inventory inventory = new Inventory();

        Lease currentLease = leaseRepository.findFirstByPropertyIdOrderByRentalStartDateDesc(propertyId).orElseThrow(() -> new PropertyNotAssociatedWithAnyLeaseException(propertyId));

        inventory.setLease(currentLease);
        inventory.setAgent(thirdPartyRepository.findById(agentId).orElseThrow(() -> new AgentNotFoundException(agentId)));
        inventory.setInventoryDate(new Date());

        InventoryTypeLabel inventoryTypeLabel = inventoryRepository.findLastInventoryType(propertyId).equals(InventoryTypeLabel.ENTREE.getLabel())?InventoryTypeLabel.SORTIE:InventoryTypeLabel.ENTREE;
        inventory.setInventoryType(inventoryTypeRepository.findByLabel(inventoryTypeLabel));

        inventoryRepository.save(inventory);

        return inventory.getId();
    }


    public RoomDto getCurrentRoom(UUID id) throws InventoryNotFoundException, RoomNotFoundException {
        inventoryRepository.findById(id).orElseThrow(()
                -> new InventoryNotFoundException(id));

        Property property = propertyRepository.findByInventoryId(id);
        Room room = roomRepository.findCurrentRoomByInventoryIdAndPropertyId(id, property.getId()).orElseThrow(()
                -> new RoomNotFoundException(id, property.getId()));

        return roomMapper.fromRoom(room);
    }

    public List<ElementSummaryDto> getElements(UUID id) throws InventoryNotFoundException, RoomNotFoundException {
        inventoryRepository.findById(id).orElseThrow(()
                -> new InventoryNotFoundException(id));

        Property property = propertyRepository.findByInventoryId(id);
        Room room = roomRepository.findCurrentRoomByInventoryIdAndPropertyId(id, property.getId()).orElseThrow(()
                -> new RoomNotFoundException(id, property.getId()));

        List<String> walls = Arrays.stream(WallTypeEnum.values()).map(WallTypeEnum::getName).toList();
        List<Element> elements = elementRepository.findElementsByRoomId(room.getId(), walls);
        List<ElementSummaryDto> elementSummaryDtos = elements.stream().map(elementSummaryMapper::fromElement).toList();

        return populateElementsSummariesDto(elementSummaryDtos, id);
    }

    public List<ElementSummaryDto> getWalls(UUID id) throws InventoryNotFoundException, RoomNotFoundException {
        inventoryRepository.findById(id).orElseThrow(()
                -> new InventoryNotFoundException(id));

        Property property = propertyRepository.findByInventoryId(id);
        Room room = roomRepository.findCurrentRoomByInventoryIdAndPropertyId(id, property.getId()).orElseThrow(()
                -> new RoomNotFoundException(id, property.getId()));

        List<String> walls = Arrays.stream(WallTypeEnum.values()).map(WallTypeEnum::getName).toList();
        List<Element> elements = elementRepository.findWallsByRoomId(room.getId(), walls);
        List<ElementSummaryDto> elementSummaryDtos = elements.stream().map(elementSummaryMapper::fromElement).toList();

        return populateElementsSummariesDto(elementSummaryDtos, id);
    }

    private List<ElementSummaryDto> populateElementsSummariesDto(List<ElementSummaryDto> elementSummaryDtos, UUID currentInventoryId) {
        elementSummaryDtos =  elementSummaryDtos.stream().peek(elementSummaryDto -> {
            InventoryStateLabel inventoryLabelState = stepRepository.findLabelStateByElementId(elementSummaryDto.getId());
            String labelState = StateTypeEnum.NEW.getName();
            boolean checked = false;
            if(inventoryLabelState != null) {
                checked = currentInventoryId.equals(inventoryLabelState.getInventoryId());
                labelState = inventoryLabelState.getStateLabel();
            }

            elementSummaryDto.setNbBasePhotos(0);
            elementSummaryDto.setNbPreviousPhotos(0);
            elementSummaryDto.setPhoto("https://via.placeholder.com/150");
            elementSummaryDto.setState(labelState);
            elementSummaryDto.setChecked(checked);
        }).toList();
        return elementSummaryDtos;
    }

    public ElementDto getElement(UUID inventoryId, UUID elementId)
            throws ElementNotFoundException, FolderNotFoundException, InventoryNotFoundException,
            StepNotFoundException {

        inventoryRepository.findById(inventoryId).orElseThrow(()
                -> new InventoryNotFoundException(inventoryId));

        Element element = elementRepository.findByInventoryId(elementId, inventoryId).orElseThrow(()
                -> new ElementNotFoundException(elementId, inventoryId));

        Pageable pageable = PageRequest.of(0, 2);
        List<Step> steps = stepRepository.findStepsByElementId(elementId, pageable);

        StepReceivedDto stepDto;
        if(steps.size() > 0){
            FolderDto folder = folderResource.getFolder(steps.get(0).getRefPhotosFolder());
            stepDto = stepMapper.fromStep(steps.get(0), folder);
        }
        else{
            throw new StepNotFoundException(elementId);
        }

        FolderDto folderPreviousPhoto = null;
        if(steps.size() > 1){
            folderPreviousPhoto = folderResource.getFolder(steps.get(1).getRefPhotosFolder());
        }

        PhotoDto photo = photoResource.getPhoto(element.getPhoto());
        FolderDto folderBasePhoto = folderResource.getFolder(element.getPhotoFolder());

        return elementMapper.fromElement(element, photo, folderBasePhoto, folderPreviousPhoto, stepDto);
    }


}
