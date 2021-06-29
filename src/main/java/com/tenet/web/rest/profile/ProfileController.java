package com.tenet.web.rest.profile;

import com.tenet.web.rest.common.entity.Dependent;
import com.tenet.web.rest.common.entity.Profile;
import com.tenet.web.rest.common.repository.ProfileRepository;
import com.tenet.web.rest.profile.dto.DependentDTO;
import com.tenet.web.rest.profile.dto.ProfileDTO;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProfileController {

    @Autowired
    private ProfileRepository profileRepository;
    private ModelMapper modelMapper;

    @Autowired
    public ProfileController(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
        this.modelMapper.addConverter(populateExistingDependent);
        this.modelMapper.addConverter(handleDependentEntered);
    }

    @RequestMapping("/profiles")
    public List<ProfileDTO> getAllProfile(){

        List<Profile> profiles = profileRepository.findAll();
        System.out.println(profiles.size());
        List<ProfileDTO> profileDTOS = profiles.stream()
                .map(profile -> modelMapper.map(profile, ProfileDTO.class))
                .collect(Collectors.toList());
        return profileDTOS;
    }

    @PostMapping("/profiles")
    public ProfileDTO saveProfile(@RequestBody ProfileDTO profileDTO){

        Profile profile = modelMapper.map(profileDTO, Profile.class);
        Profile savedProfile = profileRepository.save(profile);
        return modelMapper.map(savedProfile, ProfileDTO.class);
    }

    @PutMapping("/profiles")
    public ProfileDTO updateProfile(@RequestBody ProfileDTO profileDTO) {
        Profile profile = modelMapper.map(profileDTO, Profile.class);
        Profile savedProfile = profileRepository.save(profile);
        return modelMapper.map(savedProfile, ProfileDTO.class);
    }

    Converter<Profile, ProfileDTO> populateExistingDependent = context -> {
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setId(context.getSource().getId());
        profileDTO.setFullName(context.getSource().getFullName());
        profileDTO.setContactNumber(context.getSource().getContactNumber());
        profileDTO.setDateOfBirth(context.getSource().getDateOfBirth());
        profileDTO.setEmail(context.getSource().getEmail());
        profileDTO.setPassword(context.getSource().getPassword());

        for (Dependent dependent : context.getSource().getDependents()) {
            if (dependent.getId() != null){
                profileDTO.getDependents().add(new DependentDTO(
                        dependent.getId(),
                        dependent.getFullName(),
                        dependent.getDateOfBirth(),
                        dependent.getRelationship()
                ));
            }
        }

        return profileDTO;
    };

    Converter<ProfileDTO, Profile> handleDependentEntered = new Converter<ProfileDTO, Profile>() {
        @Override
        public Profile convert(MappingContext<ProfileDTO, Profile> context) {

            if(context.getSource().getId() == null) {
                Profile profile = new Profile();
                profile.setFullName(context.getSource().getFullName());
                profile.setContactNumber(context.getSource().getContactNumber());
                profile.setDateOfBirth(context.getSource().getDateOfBirth());
                profile.setEmail(context.getSource().getEmail());
                profile.setPassword(context.getSource().getPassword());

                List<Dependent> dependents = new ArrayList<>();
                for(DependentDTO dependentDTO: context.getSource().getDependents()){
                    Dependent dependent = new Dependent();
                    dependent.setFullName(dependentDTO.getFullName());
                    dependent.setRelationship(dependentDTO.getRelationship());
                    dependent.setDateOfBirth(dependentDTO.getDateOfBirth());
                    dependents.add(dependent);
                    dependent.setProfile(profile);
                }
                profile.setDependents(dependents);

                return profile;
            } else {
                Profile existing = profileRepository.getOne(context.getSource().getId());
                existing.setFullName(context.getSource().getFullName());
                existing.setContactNumber(context.getSource().getContactNumber());
                existing.setDateOfBirth(context.getSource().getDateOfBirth());
                existing.setEmail(context.getSource().getEmail());
                //TODO update password here or not?

                for(DependentDTO dependentDTO: context.getSource().getDependents()){

                    boolean found = false;

                    for(Dependent dependent: existing.getDependents()){

                        System.out.println(dependentDTO.getId());
                        System.out.println( dependent.getId());
                        System.out.println(" dependent.getId()");
                        if(dependentDTO.getId() != null && dependentDTO.getId().equals(dependent.getId())){
                            found = true;
                            //FIXME verify if deleted?
                            Dependent existingDependent = existing.getDependents().get(0);
                            existingDependent.setFullName(dependentDTO.getFullName());
                            existingDependent.setDateOfBirth(dependentDTO.getDateOfBirth());
                            existingDependent.setRelationship(dependentDTO.getRelationship());
                            break;
                        }
                    }

                    // if no dependent added till now

                    if(!found && !dependentDTO.getFullName().isEmpty()){
                        Dependent dependent = new Dependent();
                        dependent.setFullName(dependentDTO.getFullName());
                        dependent.setRelationship(dependentDTO.getRelationship());
                        dependent.setDateOfBirth(dependentDTO.getDateOfBirth());

                        dependent.setProfile(existing);
                        existing.getDependents().add(dependent);
                    }
                }

                return existing;
            }


        }
    };

}
