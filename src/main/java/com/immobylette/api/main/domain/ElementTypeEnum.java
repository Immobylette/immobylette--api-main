package com.immobylette.api.main.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ElementTypeEnum {

    CHAIR("CHAISE"),
    TABLE("TABLE");
    
    private final String name;
}
