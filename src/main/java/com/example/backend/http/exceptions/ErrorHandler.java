package com.example.backend.http.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorHandler {
  private String error;
  private String message;
  private Number status;
}
