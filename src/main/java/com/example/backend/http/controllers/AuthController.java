package com.example.backend.http.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dtos.auth.LoginRequestDTO;
import com.example.backend.dtos.auth.LoginResponseDTO;
import com.example.backend.http.exceptions.ErrorHandler;
import com.example.backend.services.auth.LoginService;
import com.example.backend.services.auth.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  private LoginService loginService;
  @Autowired
  private TokenService tokenService;

  @PostMapping("/login")
  public ResponseEntity<Object> login(@RequestBody @Valid LoginRequestDTO body) {

    try {
      var user = this.loginService.execute(body);

      var token = this.tokenService.generateToken(user);

      return ResponseEntity.ok().body(new LoginResponseDTO(token));
    } catch (Exception e) {
      var status = HttpStatus.UNAUTHORIZED;
      var err = new ErrorHandler(status.getReasonPhrase(), "Invalid Credentials",
          status.value());
      return ResponseEntity.status(status).body(err);
    }
  }
}
