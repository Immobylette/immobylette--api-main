package com.immobylette.api.main.controller;

import com.immobylette.api.main.dto.ElementDto;
import com.immobylette.api.main.dto.ElementSummaryDto;
import com.immobylette.api.main.dto.RoomDto;
import com.immobylette.api.main.exception.ElementNotFoundException;
import com.immobylette.api.main.exception.FolderNotFoundException;
import com.immobylette.api.main.exception.InventoryNotFoundException;
import com.immobylette.api.main.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping("/inventories/{id}/room")
    public RoomDto getCurrentRoom(@PathVariable UUID id) throws InventoryNotFoundException {
        return inventoryService.getCurrentRoom(id);
    }

    @GetMapping("/inventories/{id}/elements")
    public List<ElementSummaryDto> getElements(@PathVariable UUID id) throws InventoryNotFoundException {
        return inventoryService.getElements(id);
    }

    @GetMapping("/inventories/{id}/walls")
    public List<ElementSummaryDto> getWalls(@PathVariable UUID id) throws InventoryNotFoundException {
        return inventoryService.getWalls(id);
    }

    @GetMapping("/inventories/{inventoryId}/elements/{elementId}")
    public ElementDto getElement(@PathVariable UUID inventoryId, @PathVariable UUID elementId)
            throws ElementNotFoundException, FolderNotFoundException {
        return inventoryService.getElement(inventoryId, elementId);
    }
}
