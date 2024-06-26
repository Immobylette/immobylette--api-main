package com.immobylette.api.main.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class PropertyNotAssociatedWithAnyLeaseException extends RuntimeException{
    public PropertyNotAssociatedWithAnyLeaseException(UUID id) {
        super(String.format("Property %s is not associated with any lease", id));
    }
}
