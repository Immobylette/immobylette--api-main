package com.immobylette.api.main.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SignatureTypeEnum {
    AGENT("AGENT"),
    TENANT("TENANT");

    private final String name;
}
