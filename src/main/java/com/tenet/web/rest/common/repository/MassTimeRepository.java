package com.tenet.web.rest.common.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.tenet.web.rest.common.entity.MassTime;

@Repository
public interface MassTimeRepository extends JpaRepository<MassTime, Long>, JpaSpecificationExecutor<MassTime> {

	List<MassTime> findByDate(LocalDate date);

	long countByDateAndTime(LocalDate date, LocalTime time);
}
