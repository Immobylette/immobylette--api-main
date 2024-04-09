package com.immobylette.api.main.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class InventoryDto {

    @NotNull
    private UUID id;

    @NotNull
    private PropertyDto property;

    @NotNull
    private ThirdPartyDto agent;

    @NotNull
    private ThirdPartyDto tenant;
}
