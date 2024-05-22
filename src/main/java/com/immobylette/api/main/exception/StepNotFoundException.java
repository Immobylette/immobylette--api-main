package com.immobylette.api.main.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StepNotFoundException extends RuntimeException{
    public StepNotFoundException(UUID elementId) {
        super(String.format("Step not found for the element %s", elementId));
    }
}
