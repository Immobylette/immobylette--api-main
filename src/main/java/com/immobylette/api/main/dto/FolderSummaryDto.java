package com.immobylette.api.main.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Builder
@Setter
@Getter
public class FolderSummaryDto {
    @NotNull
    @JsonProperty("id")
    private UUID id;

    @NotNull
    @JsonProperty("nb_photos")
    private Integer nbPhotos;
}
