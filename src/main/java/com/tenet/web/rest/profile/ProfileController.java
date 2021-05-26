package com.tenet.web.rest.profile;

import com.tenet.web.rest.profile.dto.ProfileListResponse;
import com.tenet.web.rest.profile.dto.ProfileRequestMapper;
import com.tenet.web.rest.profile.dto.ProfileRequestResponse;
import com.tenet.web.rest.common.repository.ProfileRepository;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProfileController {

    @Autowired
    private ProfileRequestMapper profileRequestMapper;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/front/profiles")
    public ResponseEntity<ProfileListResponse> getProfiles(Pageable pageable){
        val profiles = profileRepository.findAll(pageable);
        Page<ProfileRequestResponse> response = profiles.map(profileRequestMapper::convertToDto);
        return ResponseEntity.ok(modelMapper.map(response, ProfileListResponse.class));

    }

    @GetMapping("/front/profiles/{profileId}")
    public ResponseEntity<ProfileRequestResponse> getProfileById(@PathVariable String profileId){
        val profile = profileRepository.findById(profileId);
        return profile.map(
                r -> ResponseEntity.ok(profileRequestMapper.convertToDto(r)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
        );
    }

    @PostMapping("/front/profiles")
    public ResponseEntity<ProfileRequestResponse> addProfile(@RequestBody ProfileRequestResponse profileRequest){
        val profile =profileRequestMapper.convertToEntity(profileRequest);
        val updatedProfile = profileRepository.save(profile);
        val response = profileRequestMapper.convertToDto(updatedProfile);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
