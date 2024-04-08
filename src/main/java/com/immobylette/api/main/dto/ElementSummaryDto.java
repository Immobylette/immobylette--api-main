package com.immobylette.api.main.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Builder
@Getter
public class ElementSummaryDto {

    private UUID id;

    private String description;

    private String type;

    private String photo;

    private Map<String, String> attributes;

    @JsonProperty("nb_base_photos")
    private Integer nbBasePhotos;

    @JsonProperty("nb_previous_photos")
    private Integer nbPreviousPhotos;

    private String state;
}
