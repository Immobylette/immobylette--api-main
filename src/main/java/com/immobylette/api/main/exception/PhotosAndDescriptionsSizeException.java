package com.immobylette.api.main.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PhotosAndDescriptionsSizeException extends RuntimeException{
    public PhotosAndDescriptionsSizeException() {
        super("The photo list and the description list are not the same size");
    }
}
