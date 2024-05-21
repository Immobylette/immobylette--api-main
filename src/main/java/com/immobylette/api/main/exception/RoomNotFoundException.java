package com.immobylette.api.main.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RoomNotFoundException extends RuntimeException{
    public RoomNotFoundException(UUID inventoryId, UUID propertyId) {
        super(String.format("Room not found for inventoryId %s and %s propertyId %s", inventoryId, propertyId));
    }
}
