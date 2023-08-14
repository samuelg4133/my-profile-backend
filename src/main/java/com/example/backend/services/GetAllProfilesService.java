package com.example.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.backend.domain.models.Profile;
import com.example.backend.domain.repositories.ProfileRepository;

@Service
public class GetAllProfilesService {

  @Autowired
  private ProfileRepository profileRepository;

  public Page<Profile> execute(Pageable pageable) {
    var profiles = this.profileRepository.findAll(pageable);

    return profiles;
  }

}
