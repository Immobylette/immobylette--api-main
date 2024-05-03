package com.immobylette.api.main.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum InventoryTypeLabel {
    ENTREE("ENTREE"),
    SORTIE("SORTIE");

    private final String label;
}
