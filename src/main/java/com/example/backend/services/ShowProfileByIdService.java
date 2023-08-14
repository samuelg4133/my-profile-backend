package com.example.backend.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.domain.errors.NotFoundException;
import com.example.backend.domain.models.Profile;
import com.example.backend.domain.repositories.ProfileRepository;

@Service
public class ShowProfileByIdService {

  @Autowired
  private ProfileRepository profileRepository;

  public Profile execute(UUID id) {
    var profile = this.profileRepository.findById(id);

    return profile.orElseThrow(NotFoundException::new);
  }
}
