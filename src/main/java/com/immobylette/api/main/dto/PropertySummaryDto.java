package com.immobylette.api.main.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class PropertySummaryDto {

    private UUID id;

    @JsonProperty("property_class")
    private String propertyClass;

    @JsonProperty("property_type")
    private String propertyType;

    private AddressDto address;

    @JsonProperty("curent_inventory")
    private Boolean curentInventory;

    private float distance;

    private UUID photo;
}
