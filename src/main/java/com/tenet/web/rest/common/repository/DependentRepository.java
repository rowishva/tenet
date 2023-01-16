package com.tenet.web.rest.common.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tenet.web.rest.common.entity.Dependent;

@Repository
public interface DependentRepository extends JpaRepository<Dependent, Long> {

	List<Dependent> findByProfileId(Long id);

	long countByFullNameAndDateOfBirth(String fullName, LocalDate dateOfBirth);
}
