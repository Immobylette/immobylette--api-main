package com.immobylette.api.main.service;

import com.immobylette.api.main.domain.InventoryStateLabel;
import com.immobylette.api.main.domain.SignatureTypeEnum;
import com.immobylette.api.main.domain.StateTypeEnum;
import com.immobylette.api.main.domain.WallTypeEnum;
import com.immobylette.api.main.dto.*;
import com.immobylette.api.main.entity.Inventory;
import com.immobylette.api.main.entity.Property;
import com.immobylette.api.main.entity.Room;
import com.immobylette.api.main.entity.Step;
import com.immobylette.api.main.entity.ThirdParty;
import com.immobylette.api.main.entity.Element;
import com.immobylette.api.main.entity.Lease;
import com.immobylette.api.main.entity.SignatureInventoryThirdParty;
import com.immobylette.api.main.exception.*;
import com.immobylette.api.main.mapper.ElementMapper;
import com.immobylette.api.main.mapper.ElementSummaryMapper;
import com.immobylette.api.main.mapper.InventorySummaryMapper;
import com.immobylette.api.main.mapper.RoomMapper;
import com.immobylette.api.main.repository.ThirdPartyRepository;
import com.immobylette.api.main.repository.InventoryRepository;
import com.immobylette.api.main.repository.InventoryTypeRepository;
import com.immobylette.api.main.repository.LeaseRepository;
import com.immobylette.api.main.repository.PropertyRepository;
import com.immobylette.api.main.repository.RoomRepository;
import com.immobylette.api.main.repository.SignatureInventoryThirdPartyRepository;
import com.immobylette.api.main.repository.ElementRepository;
import com.immobylette.api.main.repository.StepRepository;
import com.immobylette.api.main.entity.enums.InventoryTypeLabel;
import com.immobylette.api.main.resource.FolderResource;
import com.immobylette.api.main.resource.PhotoResource;
import lombok.AllArgsConstructor;

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
    private final SignatureInventoryThirdPartyRepository signatureInventoryThirdPartyRepository;

    private final PropertyRepository propertyRepository;

    private final ElementRepository elementRepository;

    private final StepRepository stepRepository;

    private final RoomMapper roomMapper;

    private final ElementSummaryMapper elementSummaryMapper;

    private final ElementMapper elementMapper;

    private final PhotoResource photoResource;

    private final FolderResource folderResource;

    private final InventorySummaryMapper inventorySummaryMapper;


    public UUID createInventory(UUID propertyId, UUID agentId)
            throws PropertyNotAssociatedWithAnyLeaseException, AgentNotFoundException, InventoryAlreadyInProgressException {
        Inventory inventory = new Inventory();

        if(inventoryRepository.findCurrentInventoryByPropertyId(propertyId) != null ){
            throw new InventoryAlreadyInProgressException(propertyId);
        }

        Lease currentLease = leaseRepository.findFirstByPropertyIdOrderByRentalStartDateDesc(propertyId).orElseThrow(() -> new PropertyNotAssociatedWithAnyLeaseException(propertyId));

        inventory.setLease(currentLease);
        inventory.setAgent(thirdPartyRepository.findById(agentId).orElseThrow(() -> new AgentNotFoundException(agentId)));
        inventory.setInventoryDate(new Date());

        String lastInventoryType = inventoryRepository.findLastInventoryType(propertyId);

        InventoryTypeLabel inventoryTypeLabel = InventoryTypeLabel.ENTREE;

        if(lastInventoryType != null) {
            inventoryTypeLabel = lastInventoryType.equals(InventoryTypeLabel.ENTREE.getLabel())?InventoryTypeLabel.SORTIE:InventoryTypeLabel.ENTREE;
        }

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

    public List<ElementSummaryDto> getElements(UUID id) throws InventoryNotFoundException, RoomNotFoundException, FolderNotFoundException, GCPStorageException {
        inventoryRepository.findById(id).orElseThrow(()
                -> new InventoryNotFoundException(id));

        Property property = propertyRepository.findByInventoryId(id);
        Room room = roomRepository.findCurrentRoomByInventoryIdAndPropertyId(id, property.getId()).orElseThrow(()
                -> new RoomNotFoundException(id, property.getId()));

        List<String> walls = Arrays.stream(WallTypeEnum.values()).map(WallTypeEnum::getName).toList();
        List<Element> elements = elementRepository.findElementsByRoomId(room.getId(), walls);

        return generateElementsSummariesDto(elements, id);
    }

    public List<ElementSummaryDto> getWalls(UUID id) throws InventoryNotFoundException, RoomNotFoundException, FolderNotFoundException, GCPStorageException {
        inventoryRepository.findById(id).orElseThrow(()
                -> new InventoryNotFoundException(id));

        Property property = propertyRepository.findByInventoryId(id);
        Room room = roomRepository.findCurrentRoomByInventoryIdAndPropertyId(id, property.getId()).orElseThrow(()
                -> new RoomNotFoundException(id, property.getId()));

        List<String> walls = Arrays.stream(WallTypeEnum.values()).map(WallTypeEnum::getName).toList();
        List<Element> elements = elementRepository.findWallsByRoomId(room.getId(), walls);

        return generateElementsSummariesDto(elements, id);
    }

    private List<ElementSummaryDto> generateElementsSummariesDto(List<Element> elements, UUID currentInventoryId) throws FolderNotFoundException, GCPStorageException {
        return elements.stream().map(element -> {
            int nbBasePhotos = folderResource.getFolderSummary(element.getPhotoFolder()).getNbPhotos();
            int nbPreviousPhotos = 0;

            List<Step> steps = stepRepository.findLastTwoStepsByElementId(element.getId());

            if(steps.size() > 1){
                nbPreviousPhotos = folderResource.getFolderSummary(steps.get(1).getRefPhotosFolder()).getNbPhotos();
            }

            ElementSummaryDto elementSummaryDto = elementSummaryMapper.fromElement(element, nbBasePhotos, nbPreviousPhotos);

            InventoryStateLabel inventoryLabelState = stepRepository.findLabelStateByElementId(elementSummaryDto.getId());
            String labelState = StateTypeEnum.NEW.getName();
            boolean checked = false;
            if(inventoryLabelState != null) {
                checked = currentInventoryId.equals(inventoryLabelState.getInventoryId());
                labelState = inventoryLabelState.getStateLabel();
            }

            PhotoUrlDto photo = photoResource.getPhoto(elementSummaryDto.getPhoto());

            elementSummaryDto.setPhoto(photo.getUrl());
            elementSummaryDto.setState(labelState);
            elementSummaryDto.setChecked(checked);

            return elementSummaryDto;
        }).toList();
    }

    public ElementDto getElement(UUID inventoryId, UUID elementId)
            throws ElementNotFoundException, FolderNotFoundException, InventoryNotFoundException,
            StepNotFoundException {

        inventoryRepository.findById(inventoryId).orElseThrow(()
                -> new InventoryNotFoundException(inventoryId));

        Element element = elementRepository.findByInventoryId(elementId, inventoryId).orElseThrow(()
                -> new ElementNotFoundException(elementId, inventoryId));

        List<Step> steps = stepRepository.findLastTwoStepsByElementId(elementId);

        FolderDto folderPreviousPhoto = null;
        if(steps.size() > 1){
            folderPreviousPhoto = folderResource.getFolder(steps.get(1).getRefPhotosFolder());
        }

        String photoObjectName = String.format("%s/%s", element.getPhotoFolder().toString(), element.getPhoto().toString());

        PhotoUrlDto photo = photoResource.getPhoto(photoObjectName);
        FolderDto folderBasePhoto = folderResource.getFolder(element.getPhotoFolder());

        return elementMapper.fromElement(element, photo, folderBasePhoto, folderPreviousPhoto);
    }


    public void sign(UUID id, SignatureTypeEnum type) throws InventoryNotFoundException {
        Inventory inventory = inventoryRepository.findById(id).orElseThrow(() -> new InventoryNotFoundException(id));

        var signatureBuilder = SignatureInventoryThirdParty.builder()
                .inventory(inventory)
                .signatureDate(new Date());

        switch (type) {
            case AGENT:
                signatureBuilder.thirdParty(inventory.getAgent());
                break;
            case TENANT:
                Property property = propertyRepository.findByInventoryId(id);
                ThirdParty currentTenant = thirdPartyRepository.findCurrentTenantByPropertyId(property.getId());
                signatureBuilder.thirdParty(currentTenant);
                break;
        }

        signatureInventoryThirdPartyRepository.save(signatureBuilder.build());
    }

    public InventorySummaryDto getSummary(UUID id) throws InventoryNotFoundException, InventoryNotCompletedException, FolderNotFoundException, GCPStorageException {
        Inventory inventory = inventoryRepository.findById(id).orElseThrow(() -> new InventoryNotFoundException(id));

        if (elementRepository.countByInventoryId(id) != stepRepository.countByInventoryId(id))
            throw new InventoryNotCompletedException(id);

        int nbRooms = inventory.getLease().getProperty().getNbRooms();
        Date date = new Date();
        int nbPhotos = inventory
                .getSteps()
                .stream()
                .reduce(
                    0,
                    (prev, curr) -> prev + folderResource.getFolderSummary(curr.getRefPhotosFolder()).getNbPhotos(),
                    Integer::sum
                );

        return inventorySummaryMapper.fromInventorySummary(nbRooms, nbPhotos, date);
    }
}
