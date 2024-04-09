package com.immobylette.api.main.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Builder
@Getter
public class ElementDto {

    @NotNull
    private UUID id;

    private String description;

    @NotNull
    private String type;

    @NotNull
    private String photo;

    private Map<String, String> attributes;

    @JsonProperty("base_photos")
    private List<String> basePhotos;

    @JsonProperty("previous_photos")
    private List<String> previousPhotos;

    @NotNull
    private StepDto step;
}