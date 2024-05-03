package com.immobylette.api.main.service;

import com.immobylette.api.main.dto.RoomDto;
import com.immobylette.api.main.entity.Property;
import com.immobylette.api.main.entity.Room;
import com.immobylette.api.main.exception.InventoryNotFoundException;
import com.immobylette.api.main.mapper.RoomMapper;
import com.immobylette.api.main.repository.PropertyRepository;
import com.immobylette.api.main.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class InventoryService {
    private final RoomRepository roomRepository;
    private final PropertyRepository propertyRepository;

    private final RoomMapper roomMapper;


    public RoomDto getCurrentRoom(UUID id) throws InventoryNotFoundException {
        Property property = propertyRepository.findByInventoryId(id);
        Room room = roomRepository.findCurrentRoomByInventoryIdAndRoomId(id, property.getId());
        if (room == null) {
            throw new InventoryNotFoundException(id);
        }
        return roomMapper.fromRoom(room);
    }
}
