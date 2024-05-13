package com.immobylette.api.main.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WallTypeEnum {

    WALL("MUR"),
    FLOOR("SOL"),
    CEILING("PLAFOND");

    private final String name;
}
