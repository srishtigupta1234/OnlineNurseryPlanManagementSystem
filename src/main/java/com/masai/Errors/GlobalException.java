package com.masai.Errors;


import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(LoginException.class)
    public ResponseEntity<MyErrorDetails> loginExceptionHandler(LoginException e, WebRequest wr) {
        return new ResponseEntity<>(new MyErrorDetails(LocalDateTime.now(), e.getMessage(), wr.getDescription(false)), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PlanterException.class)
    public ResponseEntity<MyErrorDetails> planterExceptionHandler(PlanterException e, WebRequest wr) {
        return new ResponseEntity<>(new MyErrorDetails(LocalDateTime.now(), e.getMessage(), wr.getDescription(false)), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PlantException.class)
    public ResponseEntity<MyErrorDetails> plantExceptionHandler(PlantException e, WebRequest wr) {
        return new ResponseEntity<>(new MyErrorDetails(LocalDateTime.now(), e.getMessage(), wr.getDescription(false)), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SeedException.class)
    public ResponseEntity<MyErrorDetails> seedExceptionHandler(SeedException e, WebRequest wr) {
        return new ResponseEntity<>(new MyErrorDetails(LocalDateTime.now(), e.getMessage(), wr.getDescription(false)), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OrderException.class)
    public ResponseEntity<MyErrorDetails> orderExceptionHandler(OrderException e, WebRequest wr) {
        return new ResponseEntity<>(new MyErrorDetails(LocalDateTime.now(), e.getMessage(), wr.getDescription(false)), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomerException.class)
    public ResponseEntity<MyErrorDetails> customerExceptionHandler(CustomerException e, WebRequest wr) {
        return new ResponseEntity<>(new MyErrorDetails(LocalDateTime.now(), e.getMessage(), wr.getDescription(false)), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AdminException.class)
    public ResponseEntity<MyErrorDetails> adminExceptionHandler(AdminException e, WebRequest wr) {
        return new ResponseEntity<>(new MyErrorDetails(LocalDateTime.now(), e.getMessage(), wr.getDescription(false)), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MyErrorDetails> globalExceptionHandler(Exception e, WebRequest wr) {
        return new ResponseEntity<>(new MyErrorDetails(LocalDateTime.now(), e.getMessage(), wr.getDescription(false)), HttpStatus.BAD_REQUEST);
    }
}
