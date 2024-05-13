package com.immobylette.api.main.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.UUID;

@Builder
@Getter
@Setter
public class ElementSummaryDto {

    @NotNull
    private UUID id;

    private String description;

    @NotNull
    private String type;

    @NotNull
    private String photo;

    private Map<String, String> attributes;

    @NotNull
    @JsonProperty("nb_base_photos")
    private Integer nbBasePhotos;

    @NotNull
    @JsonProperty("nb_previous_photos")
    private Integer nbPreviousPhotos;

    @NotNull
    private String state;
}
