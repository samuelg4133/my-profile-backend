package com.example.backend.dtos;

import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.example.backend.domain.models.Profile;

import lombok.Getter;

@Getter
public class ListProfilesDTO {
  private Page<ShowProfileDTO> profiles;

  public ListProfilesDTO(Page<Profile> profiles) {
    var list = profiles.toList();
    var dtoList = new ArrayList<ShowProfileDTO>();

    for (Profile item : list) {
      ShowProfileDTO dto = new ShowProfileDTO(item);
      dtoList.add(dto);
    }

    this.profiles = new PageImpl<>(dtoList, profiles.getPageable(), profiles.getTotalElements());

  }

}
