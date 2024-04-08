package com.immobylette.api.main.dto;


import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class AddressDto {

    private UUID id;
    private Integer number;
    private String street;
    private Integer zip;
    private String city;
    private Integer floor;
    private String extra;
    private float latitude;
    private float longitude;
}
