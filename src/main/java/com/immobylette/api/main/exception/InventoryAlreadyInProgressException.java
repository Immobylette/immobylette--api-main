package com.immobylette.api.main.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InventoryAlreadyInProgressException extends RuntimeException {
    public InventoryAlreadyInProgressException(UUID id) {
            super(String.format("An inventory is already in progress for the property : %s", id));
        }
}
