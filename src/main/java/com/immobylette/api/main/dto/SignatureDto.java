package com.immobylette.api.main.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SignatureDto {


    @NotNull
    private String type;
}
