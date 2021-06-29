package com.tenet.web.rest.common.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@Table(name = "profiles")
//        ,
//        uniqueConstraints=@UniqueConstraint(columnNames={"email"}
@Builder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@ToString
//@Where(clause = "is_deleted=0")
public class Profile extends BaseDomain {

    private static final long serialVersionUID = 20L;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "full_name", length = 50)
    private String fullName;

    @Column(name = "contact_number", length = 16)
    private String contactNumber;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "password", length = 50)
    private String password;

    @Column(name="date_of_birth", length = 50)
    private String dateOfBirth;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Dependent> dependents;


    /**
     * Get Dependents
     * @return dependents
     */
    public List<Dependent> getDependents(){
        if(dependents == null) {
            dependents = new ArrayList<Dependent>();
        }
        return dependents;
    }

    /**
     * Sets dependents
     * @param dependents
     */
    public void setDependents(List<Dependent> dependents){
        this.dependents = dependents;
    }

    public void addDependents(String fullName, String dateOfBirth, String relationship){
        Dependent dependent = new Dependent();
        dependent.setFullName(fullName);
        dependent.setDateOfBirth(dateOfBirth);
        dependent.setRelationship(relationship);

    }
}
