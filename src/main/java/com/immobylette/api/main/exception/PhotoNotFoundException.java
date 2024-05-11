package com.immobylette.api.main.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PhotoNotFoundException extends RuntimeException{
    public PhotoNotFoundException(UUID id) {
        super(String.format("Photo %s not found", id));
    }
}
