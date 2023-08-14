package com.example.backend.domain.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend.domain.models.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, UUID> {

  Optional<Profile> findByDocument(String document);

  Optional<Profile> findByEmail(String email);

  Page<Profile> findAll(Pageable pageable);
}
