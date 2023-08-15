package com.example.backend.domain.errors;

public class InvalidCredentialsException extends RuntimeException {

  public InvalidCredentialsException() {
    super("Invalid Credentials");
  }
}