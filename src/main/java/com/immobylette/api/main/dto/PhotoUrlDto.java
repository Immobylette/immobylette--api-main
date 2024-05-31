package com.immobylette.api.main.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class PhotoUrlDto {
    @NotNull
    private String description;

    @NotNull
    private String url;
}
