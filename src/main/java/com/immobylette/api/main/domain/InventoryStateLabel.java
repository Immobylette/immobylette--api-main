package com.immobylette.api.main.domain;

import java.util.UUID;

public interface InventoryStateLabel {
    UUID getInventoryId();

    String getStateLabel();
}