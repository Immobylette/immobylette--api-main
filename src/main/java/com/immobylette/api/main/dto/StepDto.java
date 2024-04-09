package com.immobylette.api.main.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Builder
@Getter
public class StepDto {

    private String description;

    @JsonProperty("error_description")
    private String errorDescription;

    @NotNull
    private String state;

    @NotNull
    private List<MultipartFile> photos;
}