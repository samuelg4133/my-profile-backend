package com.example.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.domain.errors.ResourceAlreadyExistsException;
import com.example.backend.domain.repositories.ProfileRepository;
import com.example.backend.dtos.CreateProfileDTO;

@Service
public class CreateProfileService {

  @Autowired
  private ProfileRepository profileRepository;

  public void execute(CreateProfileDTO request) {
    var profile = request.toDomain();

    System.out.println(profile);

    var profileByEmail = this.profileRepository.findByEmail(profile.getEmail());

    if (profileByEmail.isPresent()) {
      throw new ResourceAlreadyExistsException();
    }

    var profileByDocument = this.profileRepository.findByDocument(profile.getDocument());

    if (profileByDocument.isPresent()) {
      throw new ResourceAlreadyExistsException();
    }

    this.profileRepository.save(profile);
  }

}
