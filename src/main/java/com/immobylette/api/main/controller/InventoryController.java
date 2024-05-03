package com.immobylette.api.main.controller;

import com.immobylette.api.main.dto.RoomDto;
import com.immobylette.api.main.exception.InventoryNotFoundException;
import com.immobylette.api.main.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@AllArgsConstructor
@RestController
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping("/inventories/{id}/room")
    public RoomDto getCurrentRoom(@PathVariable UUID id) throws InventoryNotFoundException {
        return inventoryService.getCurrentRoom(id);
    }
}
