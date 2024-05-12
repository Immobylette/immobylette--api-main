package com.immobylette.api.main.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class RoomDto {

    @NotNull
    private UUID id;

    @NotNull
    @JsonProperty("nb_order")
    private Integer nbOrder;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private float area;

    @NotNull
    @JsonProperty("nb_walls")
    private Integer nbWalls;

    @NotNull
    @JsonProperty("nb_windows")
    private Integer nbWindows;

    @NotNull
    @JsonProperty("nb_doors")
    private Integer nbDoors;

    private String reference;

    private String allocation;

    @JsonProperty("room_type")
    private String roomType;
}
