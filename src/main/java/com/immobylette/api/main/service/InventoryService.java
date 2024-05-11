package com.immobylette.api.main.service;

import com.immobylette.api.main.domain.ElementTypeEnum;
import com.immobylette.api.main.domain.StateTypeEnum;
import com.immobylette.api.main.domain.WallTypeEnum;
import com.immobylette.api.main.dto.ElementSummaryDto;
import com.immobylette.api.main.dto.RoomDto;
import com.immobylette.api.main.entity.Element;
import com.immobylette.api.main.entity.Inventory;
import com.immobylette.api.main.entity.Property;
import com.immobylette.api.main.entity.Room;
import com.immobylette.api.main.exception.InventoryNotFoundException;
import com.immobylette.api.main.mapper.ElementSummaryMapper;
import com.immobylette.api.main.mapper.RoomMapper;
import com.immobylette.api.main.repository.ElementRepository;
import com.immobylette.api.main.repository.PropertyRepository;
import com.immobylette.api.main.repository.RoomRepository;
import com.immobylette.api.main.repository.StepRepository;
import com.immobylette.api.main.entity.Lease;
import com.immobylette.api.main.entity.enums.InventoryTypeLabel;
import com.immobylette.api.main.exception.AgentNotFoundException;
import com.immobylette.api.main.exception.PropertyNotAssociatedWithAnyLeaseException;
import com.immobylette.api.main.repository.InventoryRepository;
import com.immobylette.api.main.repository.InventoryTypeRepository;
import com.immobylette.api.main.repository.LeaseRepository;
import com.immobylette.api.main.repository.ThirdPartyRepository;
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

    private final PropertyRepository propertyRepository;

    private final ElementRepository elementRepository;

    private final StepRepository stepRepository;

    private final RoomMapper roomMapper;

    private final ElementSummaryMapper elementSummaryMapper;

    private final RoomMapper roomMapper;

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


    public RoomDto getCurrentRoom(UUID id) throws InventoryNotFoundException {
        Property property = propertyRepository.findByInventoryId(id);
        Room room = roomRepository.findCurrentRoomByInventoryIdAndRoomId(id, property.getId()).orElseThrow(() -> new InventoryNotFoundException(id));

        return roomMapper.fromRoom(room);
    }

    public List<ElementSummaryDto> getElements(UUID id) throws InventoryNotFoundException {
        return getElementsByTypes(id, Arrays.stream(ElementTypeEnum.values()).map(ElementTypeEnum::getName).toList());
    }

    public List<ElementSummaryDto> getWalls(UUID id) throws InventoryNotFoundException {
        return getElementsByTypes(id,  Arrays.stream(WallTypeEnum.values()).map(WallTypeEnum::getName).toList());
    }

    private List<ElementSummaryDto> getElementsByTypes(UUID id, List<String> types) throws InventoryNotFoundException {
        Property property = propertyRepository.findByInventoryId(id);
        Room room = roomRepository.findCurrentRoomByInventoryIdAndRoomId(id, property.getId()).orElseThrow(() -> new InventoryNotFoundException(id));

        List<Element> elements = elementRepository.findElementsByRoomIdAndElementType(room.getId(), types);
        List<ElementSummaryDto> elementSummaryDtos = elements.stream().map(elementSummaryMapper::fromElement).toList();

        elementSummaryDtos =  elementSummaryDtos.stream().peek(elementSummaryDto -> {
            String labelState = stepRepository.findLabelStateByElementId(elementSummaryDto.getId());
            if (labelState == null) {
                labelState = StateTypeEnum.VERY_GOOD.getName();
            }
            elementSummaryDto.setNbBasePhotos(0);
            elementSummaryDto.setNbPreviousPhotos(0);
            elementSummaryDto.setPhoto("https://via.placeholder.com/150");
            elementSummaryDto.setState(labelState);
        }).toList();
        return elementSummaryDtos;
    }
}
