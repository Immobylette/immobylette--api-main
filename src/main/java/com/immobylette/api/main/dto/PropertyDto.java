package com.immobylette.api.main.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class PropertyDto {

    private UUID id;
    private float surface;

    @JsonProperty("nb_rooms")
    private Integer nbRooms;

    @JsonProperty("hot_water_type")
    private String hotWaterType;

    @JsonProperty("heating_type")
    private String heatingType;

    @JsonProperty("property_class")
    private String propertyClass;

    @JsonProperty("owner")
    private ThirdPartyDto owner;

    @JsonProperty("current_tenant")
    private ThirdPartyDto currentTenant;

    private AddressDto address;
}
