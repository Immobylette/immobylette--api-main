package com.immobylette.api.main.dto;


import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class PropertySummaryDto {

    private UUID id;
    private String property_class;
    private String property_type;
    private AddressDto address;
    private Boolean currently_inventory;
    private float distance;
    private String photo;
}
