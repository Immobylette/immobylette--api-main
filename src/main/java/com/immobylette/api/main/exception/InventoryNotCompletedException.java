package com.immobylette.api.main.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InventoryNotCompletedException extends RuntimeException{
    public InventoryNotCompletedException(UUID id) {
        super(String.format("Inventory with id %s is not completed", id));
    }
}
