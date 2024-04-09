package com.immobylette.api.main.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class PropertyDto {

    @NotNull
    private UUID id;

    @NotNull
    private float surface;

    @NotNull
    @JsonProperty("nb_rooms")
    private Integer nbRooms;

    @NotNull
    @JsonProperty("hot_water_type")
    private String hotWaterType;

    @NotNull
    @JsonProperty("heating_type")
    private String heatingType;

    @NotNull
    @JsonProperty("property_type")
    private String propertyType;

    @NotNull
    @JsonProperty("property_class")
    private String propertyClass;

    @NotNull
    @JsonProperty("owner")
    private ThirdPartyDto owner;

    @NotNull
    @JsonProperty("current_tenant")
    private ThirdPartyDto currentTenant;

    @NotNull
    private AddressDto address;

    @JsonProperty("current_inventory")
    private UUID currentInventory;

    @NotNull
    private String photo;
}
