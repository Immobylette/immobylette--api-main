package com.immobylette.api.main.dto;


import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class ThirdPartyDto {

    private UUID id;
    private String surname;
    private String name;

}
