package com.immobylette.api.main.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class PropertySummaryDto {

    @NotNull
    private UUID id;

    @NotNull
    @JsonProperty("property_class")
    private String propertyClass;

    @NotNull
    @JsonProperty("property_type")
    private String propertyType;

    @NotNull
    private AddressDto address;

    @NotNull
    @JsonProperty("current_inventory")
    private Boolean currentInventory;

    @NotNull
    private float distance;

    @NotNull
    private String photo;
}
