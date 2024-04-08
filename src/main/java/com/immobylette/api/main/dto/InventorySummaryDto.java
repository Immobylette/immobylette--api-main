package com.immobylette.api.main.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder
@Getter
public class InventorySummaryDto {

    @JsonProperty("nb_rooms")
    private Integer nbRooms;

    @JsonProperty("nb_photos")
    private Integer nbPhotos;

    private Date date;
}
