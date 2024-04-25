package com.immobylette.api.main.service;

import com.immobylette.api.main.domain.PropertyDistance;
import com.immobylette.api.main.dto.PropertyDto;
import com.immobylette.api.main.dto.PropertySummaryDto;
import com.immobylette.api.main.entity.Property;
import com.immobylette.api.main.entity.ThirdParty;
import com.immobylette.api.main.mapper.PropertyMapper;
import com.immobylette.api.main.mapper.PropertySummaryMapper;
import com.immobylette.api.main.repository.InventoryRepository;
import com.immobylette.api.main.repository.PropertyRepository;
import com.immobylette.api.main.repository.ThirdPartyRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.immobylette.api.main.exception.PropertyNotFoundException;

import java.util.UUID;

@Service
@AllArgsConstructor
public class PropertyService {

    private final PropertyRepository propertyRepository;
    private final ThirdPartyRepository thirdPartyRepository;
    private final InventoryRepository inventoryRepository;

    private final PropertyMapper propertyMapper;
    private final PropertySummaryMapper propertySummaryMapper;

    public Page<PropertySummaryDto> getProperties(int page, int perPage, double latitude, double longitude) {

        PageRequest pageRequest = PageRequest.of(page, perPage);
        Page<PropertyDistance> properties = propertyRepository.findAllOrderByDistance(latitude, longitude, pageRequest);
        return properties.map(propertyDistance -> {
            Property property = propertyDistance.getProperty();
            Double distance = propertyDistance.getDistance();

            ThirdParty currentTenant = thirdPartyRepository.findCurrentTenantByPropertyId(property.getId());
            UUID currentInventory = inventoryRepository.findCurrentInventoryByPropertyId(property.getId());
            return propertySummaryMapper.fromProperty(property, currentTenant, currentInventory != null, distance);
        });
    }

    public PropertyDto getProperty(UUID id) throws PropertyNotFoundException {
        Property property = propertyRepository.findById(id).orElseThrow(() -> new PropertyNotFoundException(id));
        ThirdParty currentTenant = thirdPartyRepository.findCurrentTenantByPropertyId(id);
        UUID currentInventory = inventoryRepository.findCurrentInventoryByPropertyId(id);

        return propertyMapper.fromProperty(property, currentTenant, currentInventory);
    }
}
