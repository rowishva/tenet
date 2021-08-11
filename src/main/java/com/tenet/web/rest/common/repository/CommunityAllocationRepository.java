package com.tenet.web.rest.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tenet.web.rest.common.entity.CommunityAllocation;

@Repository
public interface CommunityAllocationRepository extends JpaRepository<CommunityAllocation, Long> {

	CommunityAllocation findByCode(String code);
}
