package com.accenture.customers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ResourceAlreadyExist extends RuntimeException{

    public ResourceAlreadyExist(String resourceName, String resorceValue) {
        super(String.format("The resource %s with the value %s already exist", resourceName, resorceValue));
    }
}
