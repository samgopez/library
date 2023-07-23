package com.project.library.exception.handler;

import com.project.library.exception.AbstractException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = AbstractException.class)
    public ResponseEntity<MessageResponse> handleCustomException(AbstractException exception) {
        return ResponseEntity
                .status(exception.getHttpStatus())
                .body(MessageResponse.builder().message(exception.getMessage()).build());
    }
}
