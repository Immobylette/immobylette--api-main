package com.immobylette.api.main.controller;

import com.immobylette.api.main.dto.PropertyDto;
import com.immobylette.api.main.exception.PropertyNotFoundException;
import com.immobylette.api.main.service.PropertyService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@AllArgsConstructor
public class PropertyController {

    private final PropertyService propertyService;

    @GetMapping("/properties")
    public Page<PropertyDto> getProperties(
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
}
