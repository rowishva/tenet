package com.tenet.web.rest.common.specification;

import org.springframework.data.jpa.domain.Specification;

import com.tenet.web.rest.common.entity.Profile;

import net.kaczmarzyk.spring.data.jpa.domain.In;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Join;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

@Join(path = "role", alias = "r")
@And({ @Spec(path = "fullName", params = "fullName", spec = LikeIgnoreCase.class),
		@Spec(path = "contactNumber", params = "contactNumber", spec = Like.class),
		@Spec(path = "username", params = "username", spec = LikeIgnoreCase.class),
		@Spec(path = "r.roleCode", params = "roleCodeIn", paramSeparator = ',', defaultVal = "USER", spec = In.class) })
public interface ProfileSerachSpec extends Specification<Profile> {

}
