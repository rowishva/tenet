package com.tenet.web.rest.common.specification;

import org.springframework.data.jpa.domain.Specification;

import com.tenet.web.rest.common.entity.MassTime;

import net.kaczmarzyk.spring.data.jpa.domain.GreaterThanOrEqual;
import net.kaczmarzyk.spring.data.jpa.domain.In;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

@And({ @Spec(path = "status", params = "statusIn", paramSeparator = ',', defaultVal = "ACTIVE", spec = In.class),
		@Spec(path = "date", params = "dateFrom", defaultVal = "#{T(java.time.LocalDate).now()}", config = "yyyy-MM-dd", valueInSpEL = true, spec = GreaterThanOrEqual.class) })
public interface MassTimeSerachSpec extends Specification<MassTime> {

}
