package com.devsuperior.demo.controllers.exceptions;

import com.devsuperior.demo.services.exceptions.DatabaseException;
import com.devsuperior.demo.services.exceptions.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<StandardError> entityNotFound(NotFoundException e, HttpServletRequest request) {
    var err = new StandardError();
    err.setStatus(HttpStatus.NOT_FOUND.value());
    err.setMessage(e.getMessage());

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
  }

  @ExceptionHandler(DatabaseException.class)
  public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request) {
    var err = new StandardError();
    err.setStatus(HttpStatus.BAD_REQUEST.value());
    err.setMessage(e.getMessage());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
  }
}
