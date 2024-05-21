package com.immobylette.api.main.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.immobylette.api.main.domain.Photo;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StepReceivedDto {
    private String description;

    @JsonProperty("error_description")
    private String errorDescription;

    @NotNull
    private String state;

    @NotNull
    private List<PhotoDto> photos;
}
