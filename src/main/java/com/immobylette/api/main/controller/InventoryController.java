package com.immobylette.api.main.controller;

import com.immobylette.api.main.dto.*;
import com.immobylette.api.main.exception.ElementNotFoundException;
import com.immobylette.api.main.exception.GCPStorageException;
import com.immobylette.api.main.dto.SignatureDto;
import com.immobylette.api.main.exception.InventoryNotFoundException;
import com.immobylette.api.main.exception.*;
import com.immobylette.api.main.service.InventoryService;
import jakarta.validation.Valid;
import com.immobylette.api.main.service.StepService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
public class InventoryController {
    private final InventoryService inventoryService;
    private final StepService stepService;

    @GetMapping("/inventories/{id}/room")
    public RoomDto getCurrentRoom(@PathVariable UUID id)
            throws InventoryNotFoundException, RoomNotFoundException {
        return inventoryService.getCurrentRoom(id);
    }

    @GetMapping("/inventories/{id}/elements")
    public List<ElementSummaryDto> getElements(@PathVariable UUID id)
            throws InventoryNotFoundException , RoomNotFoundException{
        return inventoryService.getElements(id);
    }

    @GetMapping("/inventories/{id}/walls")
    public List<ElementSummaryDto> getWalls(@PathVariable UUID id)
            throws InventoryNotFoundException, RoomNotFoundException {
        return inventoryService.getWalls(id);
    }

    @GetMapping("/inventories/{inventoryId}/elements/{elementId}")
    public ElementDto getElement(@PathVariable UUID inventoryId, @PathVariable UUID elementId)
            throws ElementNotFoundException, FolderNotFoundException, InventoryNotFoundException, StepNotFoundException {
        return inventoryService.getElement(inventoryId, elementId);
    }


    @PostMapping("/inventories/{id}/sign")
    public void sign(@PathVariable UUID id, @Valid @RequestBody SignatureDto signature) throws InventoryNotFoundException {
        inventoryService.sign(id, signature.getType());

    }

    @PostMapping("/inventories/{id}/elements/{idElement}")
    public void addStep(@PathVariable UUID id,
                        @PathVariable UUID idElement,
                        @RequestPart("photos") List<MultipartFile> photos,
                        @Valid @RequestPart("step") StepDto step
    ) throws InventoryNotFoundException, ElementNotFoundException, GCPStorageException {
        stepService.createStep(id, idElement, step, photos);
    }

    @GetMapping("/inventories/{id}/summary")
    public InventorySummaryDto getInventorySummary(@PathVariable UUID id)
            throws InventoryNotFoundException, InventoryNotCompletedException, FolderNotFoundException, GCPStorageException {
        return inventoryService.getSummary(id);
    }
}
