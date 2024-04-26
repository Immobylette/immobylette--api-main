package com.immobylette.api.main.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class ThirdPartyDto {

    @NotNull
    private UUID id;

    @NotNull
    private String surname;

    @NotNull
    private String name;

    @NotNull
    private String photo;
}
