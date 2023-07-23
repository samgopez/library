package com.project.library.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends AbstractException {
    public NotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
