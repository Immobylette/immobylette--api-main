package com.immobylette.api.main.mapper;

import com.immobylette.api.main.dto.PhotoDto;
import com.immobylette.api.main.dto.PropertyDto;
import com.immobylette.api.main.entity.*;
import org.mapstruct.*;

import java.util.UUID;

@Mapper(componentModel = "spring", uses = ThirdPartyMapper.class)
public interface PropertyMapper {

    @Mappings({
            @Mapping(target = "id", source = "property.id"),
            @Mapping(target = "hotWaterType", source = "property.hotWaterType", qualifiedByName = "hotWaterType"),
            @Mapping(target = "heatingType", source = "property.heatingType", qualifiedByName = "heatingType"),
            @Mapping(target = "propertyType", source = "property.propertyType", qualifiedByName = "propertyType"),
            @Mapping(target = "propertyClass", source = "property.propertyClass", qualifiedByName = "propertyClass"),
            @Mapping(target = "currentInventory", source = "currentInventory"),
            @Mapping(target = "photo", source = "photo.url"),
    })
    PropertyDto fromProperty(Property property, ThirdParty currentTenant, PhotoDto photo, UUID currentInventory);

    @Named("hotWaterType")
    static String hotWaterType(HotWaterType hotWaterType) {
        return hotWaterType.getLabel();
    }

    @Named("heatingType")
    static String heatingType(HeatingType heatingType) {
        return heatingType.getLabel();
    }

    @Named("propertyType")
    static String propertyType(PropertyType propertyType) {
        return propertyType.getLabel();
    }

    @Named("propertyClass")
    static String propertyClass(PropertyClass propertyClass) {
        return propertyClass.getLabel();
    }
}
