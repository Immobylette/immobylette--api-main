package com.immobylette.api.main.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ElementNotFoundException extends RuntimeException{
    public ElementNotFoundException(UUID elementId, UUID inventoryId) {
        super(String.format("Element %s not found in inventory %s", elementId, inventoryId));
    }
}
