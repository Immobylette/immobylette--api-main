package com.immobylette.api.main.mapper;

import com.immobylette.api.main.dto.PhotoDto;
import com.immobylette.api.main.dto.PropertySummaryDto;
import com.immobylette.api.main.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;


@Mapper(componentModel = "spring", uses = ThirdPartyMapper.class)
public interface PropertySummaryMapper {

    @Mappings({
            @Mapping(target = "id", source = "property.id"),
            @Mapping(target = "propertyType", source = "property.propertyType", qualifiedByName = "propertyType"),
            @Mapping(target = "propertyClass", source = "property.propertyClass", qualifiedByName = "propertyClass"),
            @Mapping(target = "currentInventory", source = "currentInventory"),
            @Mapping(target = "distance", source = "distance"),
            @Mapping(target = "photo", source = "photo.url"),
    })
    PropertySummaryDto fromProperty(Property property, ThirdParty currentTenant, PhotoDto photo, boolean currentInventory, double distance);

    @Named("propertyType")
    static String propertyType(PropertyType propertyType) {
        return propertyType.getLabel();
    }

    @Named("propertyClass")
    static String propertyClass(PropertyClass propertyClass) {
        return propertyClass.getLabel();
    }
}
