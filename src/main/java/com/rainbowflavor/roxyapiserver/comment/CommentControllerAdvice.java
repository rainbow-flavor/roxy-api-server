package com.rainbowflavor.roxyapiserver.comment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommentControllerAdvice{
    @ExceptionHandler(CommentNotFoundException.class)
    public ResponseEntity<?> notFoundResponse(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
