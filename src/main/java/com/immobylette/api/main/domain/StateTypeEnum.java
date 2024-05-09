package com.immobylette.api.main.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StateTypeEnum {

    NEW("NEUF"),
    VERY_GOOD("TRES BON"),
    GOOD("BON"),
    MEDIOCRE("MEDIOCRE"),
    BAD("MAUVAIS"),
    VERY_BAD("TRES MAUVAIS");

    private final String name;
}
