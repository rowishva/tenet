package com.tenet.web.rest.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tenet.web.rest.common.entity.GlobalParameter;

@Repository
public interface GlobalParameterRepository extends JpaRepository<GlobalParameter, Long> {

	GlobalParameter findByCode(String code);
}
