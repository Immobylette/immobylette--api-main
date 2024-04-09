package com.immobylette.api.main.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ThirdPartyTypeEnum {

    AGENT("AGENT"),
    TENANT("TENANT"),
    OWNER("OWNER");

    private final String name;
}
