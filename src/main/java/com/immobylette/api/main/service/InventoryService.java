package com.immobylette.api.main.service;

import com.immobylette.api.main.dto.RoomDto;
import com.immobylette.api.main.entity.Property;
import com.immobylette.api.main.entity.Room;
import com.immobylette.api.main.exception.InventoryNotFoundException;
import com.immobylette.api.main.mapper.RoomMapper;
import com.immobylette.api.main.repository.PropertyRepository;
import com.immobylette.api.main.repository.RoomRepository;
import com.immobylette.api.main.entity.Inventory;
import com.immobylette.api.main.exception.AgentNotFoundException;
import com.immobylette.api.main.exception.PropertyNotAssociatedWithAnyLeaseException;
import com.immobylette.api.main.repository.InventoryRepository;
import com.immobylette.api.main.repository.InventoryTypeRepository;
import com.immobylette.api.main.repository.LeaseRepository;
import com.immobylette.api.main.repository.ThirdPartyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.Date;
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

    private final RoomMapper roomMapper;

    public UUID createInventory(UUID propertyId, UUID agentId) {
        Inventory inventory = new Inventory();

        inventory.setLease(leaseRepository.getLastLeaseByPropertyId(propertyId).orElseThrow(() -> new PropertyNotAssociatedWithAnyLeaseException(propertyId)));
        inventory.setAgent(thirdPartyRepository.findById(agentId).orElseThrow(() -> new AgentNotFoundException(agentId)));
        inventory.setInventoryDate(new Date());

        String inventoryTypeLabel = inventoryRepository.findLastInventoryType(propertyId).equals("ENTREE")?"SORTIE":"ENTREE";
        inventory.setInventoryType(inventoryTypeRepository.findByLabel(inventoryTypeLabel));

        inventoryRepository.save(inventory);

        return inventory.getId();
    }

    public RoomDto getCurrentRoom(UUID id) throws InventoryNotFoundException {
        Property property = propertyRepository.findByInventoryId(id);
        Room room = roomRepository.findCurrentRoomByInventoryIdAndRoomId(id, property.getId()).orElseThrow(() -> new InventoryNotFoundException(id));

        return roomMapper.fromRoom(room);
    }
}
