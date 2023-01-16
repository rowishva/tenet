package com.tenet.web.rest.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tenet.web.rest.common.entity.SeatingCategory;

@Repository
public interface SeatingCategoryRepository extends JpaRepository<SeatingCategory, Long> {

	SeatingCategory findByTag(String tag);

	@Query("SELECT SUM(sc.totalCapacity) FROM SeatingCategory sc")
	int getTotalCapacity();
}
