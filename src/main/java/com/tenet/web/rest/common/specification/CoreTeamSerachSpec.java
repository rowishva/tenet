package com.tenet.web.rest.common.specification;

import org.springframework.data.jpa.domain.Specification;

import com.tenet.web.rest.common.entity.MassBooking;

import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

@And({ @Spec(path = "mass_time_id", params = "massTimeId", spec = Equal.class),
		@Spec(path = "fullName", params = "fullName", spec = LikeIgnoreCase.class),
		@Spec(path = "contactNumber", params = "contactNumber", spec = Like.class),
		@Spec(path = "booked", spec = Equal.class, constVal = "true") })
public interface CoreTeamSerachSpec extends Specification<MassBooking> {

}
