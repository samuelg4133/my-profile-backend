package com.example.backend.domain.errors;

public class TokenException extends RuntimeException {

  public TokenException(String message) {
    super("message");
  }
}
