package com.accenture.accounts.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFound extends RuntimeException {

    public ResourceNotFound(String resourceType, String resourceValue, String details) {
        super(String.format("%s not found: %s (%s)", resourceType, resourceValue, details));
    }

}
