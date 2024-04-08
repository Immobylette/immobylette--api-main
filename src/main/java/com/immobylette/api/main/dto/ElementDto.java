package com.immobylette.api.main.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Builder
@Getter
public class ElementDto {

    private UUID id;

    private String description;

    private String type;

    private UUID photo;

    private Map<String, String> attributes;

    @JsonProperty("base_photos")
    private List<String> basePhotos;

    @JsonProperty("previous_photos")
    private List<String> previousPhotos;

    private StepDto step;
}
