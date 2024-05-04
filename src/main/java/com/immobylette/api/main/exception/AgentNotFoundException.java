package com.immobylette.api.main.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class AgentNotFoundException extends RuntimeException{
    public AgentNotFoundException(UUID id) {
        super(String.format("Agent %s not found", id));
    }
}
