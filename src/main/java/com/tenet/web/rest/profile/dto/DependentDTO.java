package com.tenet.web.rest.profile.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DependentDTO {
    private String id;
    private String fullName;
    private String dateOfBirth;
    private String relationship;


}
