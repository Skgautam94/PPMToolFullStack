package com.gautamfullstack.ppmtool.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProjectNotFoundException extends RuntimeException {
    private String identifier;
    public ProjectNotFoundException(String identifier, String message) {
        super(message);
        this.identifier = identifier;
    }
}
