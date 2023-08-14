package com.example.backend.dtos;

import java.util.UUID;

import com.example.backend.domain.models.Profile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateProfileDTO {
  private UUID id;

  @NotNull
  private String name;

  @Email
  @NotNull
  private String email;

  public UpdateProfileDTO(String name, @Email String email) {
    this.email = email;
    this.name = name;
  }

  public Profile toUpdate(Profile profile) {
    profile.setEmail(this.email.toLowerCase());
    profile.setName(this.name.trim().toUpperCase());
    return profile;
  }
}
