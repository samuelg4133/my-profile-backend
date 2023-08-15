package com.example.backend.http.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.backend.domain.errors.InvalidCredentialsException;
import com.example.backend.domain.errors.NotFoundException;
import com.example.backend.domain.errors.ResourceAlreadyExistsException;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ResourceAlreadyExistsException.class)
  @ResponseBody
  public ResponseEntity<ErrorHandler> handleResourceAlreadyExistsException(
      ResourceAlreadyExistsException ex) {
    var err = new ErrorHandler("Conflict", ex.getMessage(), HttpStatus.CONFLICT.value());
    return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
  }

  @ExceptionHandler(InvalidCredentialsException.class)
  @ResponseBody
  public ResponseEntity<ErrorHandler> handleInvalidCredentialsException(
      ResourceAlreadyExistsException ex) {
    var status = HttpStatus.UNAUTHORIZED;
    var err = new ErrorHandler(status.getReasonPhrase(), ex.getMessage(),
        status.value());
    return ResponseEntity.status(status).body(err);
  }

  @ExceptionHandler(NotFoundException.class)
  @ResponseBody
  public ResponseEntity<ErrorHandler> handleNotFoundException(
      NotFoundException ex) {
    var err = new ErrorHandler("Not Found", ex.getMessage(), HttpStatus.NOT_FOUND.value());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  @ResponseBody
  public ResponseEntity<ErrorHandler> handleIllegalArgumentException(
      IllegalArgumentException ex) {
    var err = new ErrorHandler("Bad Request", ex.getMessage(), HttpStatus.BAD_REQUEST.value());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
  }
}