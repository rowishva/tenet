package com.tenet.web.rest.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tenet.web.rest.common.entity.MassCoreTeam;

@Repository
public interface MassCoreTeamRepository extends JpaRepository<MassCoreTeam, Long> {

	List<MassCoreTeam> findByMassTimeId(long massTimeId);
}
