package com.example.backend.http.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dtos.CreateProfileDTO;
import com.example.backend.dtos.ListProfilesDTO;
import com.example.backend.dtos.ShowProfileDTO;
import com.example.backend.dtos.UpdateProfileDTO;
import com.example.backend.services.CreateProfileService;
import com.example.backend.services.GetAllProfilesService;
import com.example.backend.services.ShowProfileByIdService;
import com.example.backend.services.UpdateProfileService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

  @Autowired
  private CreateProfileService createProfile;

  @Autowired
  private GetAllProfilesService getAllProfiles;

  @Autowired
  private ShowProfileByIdService showProfileById;

  @Autowired
  private UpdateProfileService updateProfile;

  @GetMapping
  public ResponseEntity<Page<ShowProfileDTO>> index(@RequestParam(defaultValue = "1") int page,
      @RequestParam(defaultValue = "10") int size) {
    var pageable = PageRequest.of(page - 1, size);

    var profiles = this.getAllProfiles.execute(pageable);

    var response = new ListProfilesDTO(profiles).getProfiles();

    return ResponseEntity.ok().body(response);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ShowProfileDTO> show(@PathVariable("id") UUID id) {
    var profile = this.showProfileById.execute(id);

    return ResponseEntity.ok().body(new ShowProfileDTO(profile));
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void create(@RequestBody @Valid CreateProfileDTO body) {
    this.createProfile.execute(body);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void update(@RequestBody @Valid UpdateProfileDTO body, @PathVariable("id") UUID id) {
    body.setId(id);

    this.updateProfile.execute(body);
  }
}
