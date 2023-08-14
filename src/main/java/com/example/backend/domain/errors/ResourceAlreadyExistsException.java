package com.example.backend.domain.errors;

public class ResourceAlreadyExistsException extends RuntimeException {

  public ResourceAlreadyExistsException(String message) {
    super(message);
  }

  public ResourceAlreadyExistsException() {
    super("Resource already exists");
  }
}