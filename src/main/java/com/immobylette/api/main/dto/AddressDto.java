package com.immobylette.api.main.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class AddressDto {

    @NotNull
    private UUID id;

    @NotNull
    private Integer number;

    @NotNull
    private String street;

    @NotNull
    private Integer zip;

    @NotNull
    private String city;

    private Integer floor;

    private String extra;

    @NotNull
    private float latitude;
    @NotNull
    private float longitude;
}
