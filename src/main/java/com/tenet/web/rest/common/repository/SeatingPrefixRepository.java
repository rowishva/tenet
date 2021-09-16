package com.tenet.web.rest.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tenet.web.rest.common.entity.SeatingPrefix;

@Repository
public interface SeatingPrefixRepository extends JpaRepository<SeatingPrefix, Long> {

	SeatingPrefix findByPrefix(String prefix);

	@Query("SELECT SUM(sp.allocationCapacity) FROM SeatingPrefix sp")
	int getTotalCapacity();
}
