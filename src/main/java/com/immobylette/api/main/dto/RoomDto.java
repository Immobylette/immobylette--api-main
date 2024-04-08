package com.immobylette.api.main.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class RoomDto {

    private UUID id;

    @JsonProperty("nb_order")
    private Integer nbOrder;

    private String description;

    private float area;

    @JsonProperty("nb_walls")
    private Integer nbWalls;

    @JsonProperty("nb_windows")
    private Integer nbWindows;

    @JsonProperty("nb_doors")
    private Integer nbDoors;

    private String reference;

    private String allocation;

    @JsonProperty("room_type")
    private String roomType;
}
