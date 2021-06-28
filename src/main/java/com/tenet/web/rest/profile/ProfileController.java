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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;
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
        System.out.print("this is profile");
        return profileDTOS;
    }

    @PostMapping("/profiles")
    public ProfileDTO saveProfile(ProfileDTO profileDTO){

        Profile profile = modelMapper.map(profileDTO, Profile.class);
        Profile savedProfile = profileRepository.save(profile);
        //FIXME ClassCastException
        return modelMapper.map(savedProfile, (Type) Profile.class);
    }

    //TODO replace with lambdas
    Converter<Profile, ProfileDTO> populateExistingDependent = new Converter<Profile, ProfileDTO>() {
        @Override
        public ProfileDTO convert(MappingContext<Profile, ProfileDTO> context) {

            context.getDestination().setId(context.getSource().getId());
            context.getDestination().setFullName(context.getSource().getFullName());
            context.getDestination().setContactNumber(context.getSource().getContactNumber());
            context.getDestination().setDateOfBirth(context.getSource().getDateOfBirth());
            context.getDestination().setEmail(context.getSource().getEmail());
            context.getDestination().setPassword(context.getSource().getPassword());

            context.getDestination().getDependents().add(new DependentDTO());

            for (Dependent dependent : context.getSource().getDependents()) {
                context.getDestination().getDependents().add(new DependentDTO(
                        dependent.getId(),
                        dependent.getFullName(),
                        dependent.getDateOfBirth(),
                        dependent.getRelationship()
                ));
            }

            return context.getDestination();
        }
    };

    //TODO replace with lambdas
    Converter<ProfileDTO, Profile> handleDependentEntered = new Converter<ProfileDTO, Profile>() {
        @Override
        public Profile convert(MappingContext<ProfileDTO, Profile> context) {

            context.getDestination().setId(context.getSource().getId());
            context.getDestination().setFullName(context.getSource().getFullName());
            context.getDestination().setContactNumber(context.getSource().getContactNumber());
            context.getDestination().setDateOfBirth(context.getSource().getDateOfBirth());
            context.getDestination().setEmail(context.getSource().getEmail());
            context.getDestination().setPassword(context.getSource().getPassword());

            if(context.getSource().getId() == null) {

                for(DependentDTO dependentDTO: context.getSource().getDependents()){
                    context.getDestination().addDependents(
                            dependentDTO.getFullName(),
                            dependentDTO.getDateOfBirth(),
                            dependentDTO.getRelationship()
                            );
                }
            } else {
                Profile existing = profileRepository.getOne(context.getSource().getId());
                context.getDestination().getDependents().clear();

                for(DependentDTO dependentDTO: context.getSource().getDependents()){

                    boolean found = false;

                    for(Dependent dependent: existing.getDependents()){

                        if(dependentDTO.getId() != null && dependentDTO.getId() == dependent.getId()){
                            found = true;
                            //FIXME verify if deleted?
                            dependent.setFullName(dependentDTO.getFullName());
                            dependent.setDateOfBirth(dependentDTO.getDateOfBirth());
                            dependent.setRelationship(dependentDTO.getRelationship());
                            context.getDestination().getDependents().add(dependent);

                            break;
                        }
                    }

                    // if no dependent added till now
                    if(!found && !dependentDTO.getFullName().isEmpty()){
                        context.getDestination().addDependents(
                                dependentDTO.getFullName(),
                                dependentDTO.getDateOfBirth(),
                                dependentDTO.getRelationship());
                    }
                }
            }

            return context.getDestination();
        }
    };

}
