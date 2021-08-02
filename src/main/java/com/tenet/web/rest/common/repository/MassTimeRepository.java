package com.tenet.web.rest.common.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tenet.web.rest.common.entity.MassTime;

@Repository
public interface MassTimeRepository extends JpaRepository<MassTime, Long> {

	List<MassTime> findByDate(LocalDate date);
}
