package com.immobylette.api.main.dto;


import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class InventoryDto {

    private UUID id;
    private PropertyDto property;
    private ThirdPartyDto agent;
    private ThirdPartyDto tenant;
}
