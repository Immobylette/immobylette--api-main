package com.immobylette.api.main.controller;

import com.immobylette.api.main.dto.PropertyDto;
import com.immobylette.api.main.dto.PropertySummaryDto;
import com.immobylette.api.main.exception.PropertyNotFoundException;
import com.immobylette.api.main.service.InventoryService;
import com.immobylette.api.main.service.PropertyService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
public class PropertyController {

    private final PropertyService propertyService;
    private final InventoryService inventoryService;

    @GetMapping("/properties")
    public Page<PropertySummaryDto> getProperties(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(name = "per_page", defaultValue = "10") int perPage,
            @RequestParam double latitude,
            @RequestParam double longitude
    ) {
        return propertyService.getProperties(page, perPage, latitude, longitude);
    }

    @GetMapping("/properties/{id}")
    public PropertyDto getProperty(@PathVariable UUID id) throws PropertyNotFoundException{
        return propertyService.getProperty(id);
    }

    @PostMapping("/properties/{id}/start")
    public UUID startInventory(@PathVariable UUID id,
                               @RequestPart("agent") UUID agentId) throws PropertyNotFoundException {
        return inventoryService.createInventory(id, agentId);
    }
}
