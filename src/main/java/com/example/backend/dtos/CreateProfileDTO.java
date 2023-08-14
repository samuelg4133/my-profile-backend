package com.example.backend.dtos;

import com.example.backend.domain.models.Profile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CreateProfileDTO(@NotNull String name, @NotNull String document, @Email @NotNull String email) {
  public Profile toDomain() {
    var profile = new Profile(name(), document(), email());

    return profile;
  }
}
