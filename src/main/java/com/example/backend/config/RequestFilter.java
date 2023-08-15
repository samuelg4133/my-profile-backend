package com.example.backend.config;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.backend.domain.errors.TokenException;
import com.example.backend.domain.models.User;
import com.example.backend.domain.repositories.UserRepository;
import com.example.backend.services.auth.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RequestFilter extends OncePerRequestFilter {

  @Autowired
  TokenService tokenService;

  @Autowired
  UserRepository userRepository;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    if (!request.getRequestURI().startsWith("/api/auth")) {
      var token = this.recoverToken(request);

      var userId = this.tokenService.validateToken(token);

      var userProfile = this.userRepository.findById(UUID.fromString(userId));

      User user = userProfile.orElseThrow(() -> new TokenException("Token is invalid"));

      var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(authentication);

    }

    filterChain.doFilter(request, response);
  }

  private String recoverToken(HttpServletRequest request) {
    var token = request.getHeader("Authorization");
    if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
      return null;
    }
    return token.replace("Bearer ", "");
  }

}
