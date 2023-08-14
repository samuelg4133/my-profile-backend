package com.example.backend.domain.models;

import java.sql.Timestamp;
import java.util.UUID;

import com.example.backend.domain.valueObjects.CPF;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "profiles")
@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
public class Profile {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(nullable = false)
  @NotNull
  @Max(value = 191)
  private String name;

  @Column(nullable = false, unique = true)
  @NotNull
  private String document;

  @Column(nullable = false, unique = true)
  @Email
  @NotNull
  @Max(value = 100)
  private String email;

  @Column(name = "created_at")
  private Timestamp createdAt;

  @Column(name = "updated_at")
  private Timestamp updatedAt;

  public Profile(String name, String document, String email) {
    this.document = CPF.of(document).getValue();
    this.name = name.trim().toUpperCase();
    this.email = email.toLowerCase();
  }
}
