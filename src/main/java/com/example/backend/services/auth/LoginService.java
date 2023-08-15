package com.example.backend.services.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.example.backend.domain.errors.InvalidCredentialsException;
import com.example.backend.domain.models.User;
import com.example.backend.dtos.auth.LoginRequestDTO;

@Service
public class LoginService {
  @Autowired
  private AuthenticationManager authenticationManager;

  public User execute(LoginRequestDTO request) throws InvalidCredentialsException {
    var usernamePassword = new UsernamePasswordAuthenticationToken(request.email(), request.password());

    var auth = this.authenticationManager.authenticate(usernamePassword);

    if (!auth.isAuthenticated()) {
      throw new InvalidCredentialsException();
    }

    return (User) auth.getPrincipal();
  }
}
