package com.tenet.web.rest.profile.dto;

import com.tenet.web.rest.common.entity.Dependent;
import lombok.*;


import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter
public class ProfileDTO {

    private String id;
    private String fullName;
    private String contactNumber;
    private String email;
    private String password;
    private String dateOfBirth;
    private List<DependentDTO> dependents;

    /**
     *
     * @return
     */
    public List<DependentDTO> getDependents(){
        if(dependents == null){
            dependents = new ArrayList<DependentDTO>();
        }
        return dependents;
    }

    public void setDependents(List<DependentDTO> dependents){
        this.dependents = dependents;
    }
}
