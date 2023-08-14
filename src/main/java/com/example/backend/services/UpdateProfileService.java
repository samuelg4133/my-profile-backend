package com.example.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.domain.errors.NotFoundException;
import com.example.backend.domain.errors.ResourceAlreadyExistsException;
import com.example.backend.domain.models.Profile;
import com.example.backend.domain.repositories.ProfileRepository;
import com.example.backend.dtos.UpdateProfileDTO;

@Service
public class UpdateProfileService {

  @Autowired
  private ProfileRepository profileRepository;

  public void execute(UpdateProfileDTO request) {

    var profileOptional = this.profileRepository.findById(request.getId());

    Profile profile = profileOptional.orElseThrow(NotFoundException::new);

    var profileByDocumentOrEmail = this.profileRepository.findByEmail(request.getEmail());

    if (profileByDocumentOrEmail.isPresent()) {
      Profile profileToCheck = profileByDocumentOrEmail.orElse(profile);

      if (profileToCheck.getId() != profile.getId()) {
        throw new ResourceAlreadyExistsException();
      }
    }

    profile = request.toUpdate(profile);

    this.profileRepository.save(profile);

  }
}
