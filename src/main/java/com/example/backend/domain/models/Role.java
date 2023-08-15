package com.example.backend.domain.models;

import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;

import com.example.backend.domain.enums.RoleName;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "roles")
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class Role implements GrantedAuthority {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, unique = true, name = "name")
  private RoleName roleName;

  @Override
  public String getAuthority() {
    return this.roleName.toString();
  }

  public UUID getId() {
    return this.id;
  }

}
