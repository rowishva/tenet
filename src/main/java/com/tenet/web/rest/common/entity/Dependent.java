package com.tenet.web.rest.common.entity;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Data
@Table(name = "dependents")
@Builder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "is_deleted=0")
@ToString
public class Dependent extends BaseDomain{

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "full_name", length = 50)
    private String fullName;

    @Column(name="date_of_birth", length = 20)
    private String dateOfBirth ;

    @Column(name="relationship", length = 50)
    private String relationship;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="profile_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Profile profile;

    /**
     * Get profile
     */
    public Profile getProfile(){
        return profile;
    }

    /**
     * Set profile
     * @param profile
     */
    public void setProfile(Profile profile){
        this.profile = profile;
    }

}
