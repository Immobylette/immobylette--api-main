package com.immobylette.api.main.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.immobylette.api.main.domain.StateTypeEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class StepDto {

    private String description;

    @JsonProperty("error_description")
    private String errorDescription;

    @NotNull
    private StateTypeEnum state;

    @NotNull
    @JsonProperty("photos_descriptions")
    private List<String> photosDescriptions;
}
