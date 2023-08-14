package com.example.backend.dtos;

import java.sql.Timestamp;
import java.util.UUID;

import com.example.backend.domain.models.Profile;
import com.example.backend.domain.valueObjects.CPF;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ShowProfileDTO {
  private Profile profile;

  public UUID getId() {
    return this.profile.getId();
  }

  public String getEmail() {
    return this.profile.getEmail();
  }

  public String getName() {
    return this.profile.getName();
  }

  public String getDocument() {
    return CPF.toMask(this.profile.getDocument());
  }

  public Timestamp getCreatedAt() {
    return this.profile.getCreatedAt();
  }

  public Timestamp getUpdatedAt() {
    return this.profile.getUpdatedAt();
  }

}
