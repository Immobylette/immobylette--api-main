package com.immobylette.api.main.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder
@Getter
public class InventorySummaryDto {

    @NotNull
    @JsonProperty("nb_rooms")
    private Integer nbRooms;

    @NotNull
    @JsonProperty("nb_photos")
    private Integer nbPhotos;

    @NotNull
    private Date date;
}
