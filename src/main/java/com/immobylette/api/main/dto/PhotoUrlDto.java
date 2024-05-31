package com.immobylette.api.main.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Builder
@Setter
@Getter
public class PhotoUrlDto {
    @NotNull
    private UUID id;

    @NotNull
    private String description;

    @NotNull
    private String url;
}
