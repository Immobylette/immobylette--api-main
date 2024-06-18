package com.immobylette.api.main.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.CONFLICT)
public class InspectionFinishedException extends RuntimeException{
    public InspectionFinishedException(UUID id) {
        super(String.format("Inspection with id %s is finished", id));
    }
}
