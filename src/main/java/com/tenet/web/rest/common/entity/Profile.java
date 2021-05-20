package com.tenet.web.rest.common.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;

import javax.persistence.*;


@Entity
@Data
@Table(name = "profiles",
        uniqueConstraints=@UniqueConstraint(columnNames={"full_name", "nric_passport", "email"}))
@Builder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "is_deleted=0")
public class Profile extends BaseDomain {


    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "full_name", length = 50)
    private String fullName;

    @Column(name = "nric_passport", length = 50)
    private String nricPassport;

    @Column(name = "gender", length = 10)
    private String gender;

    @Column(name = "nationality", length = 40)
    private String nationality;

    @Column(name = "parish_group", length = 50)
    private String parishGroup;

    @Column(name = "contact_number", length = 16)
    private String contactNumber;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "password", length = 20)
    private String password;

}
