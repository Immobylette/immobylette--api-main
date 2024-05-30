package com.immobylette.api.main.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.immobylette.api.main.domain.Photo;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class StepSentDto {

    private String description;

    @JsonProperty("error_description")
    private String errorDescription;

    @NotNull
    private String state;

    @NotNull
    private List<Photo> photos;
}
