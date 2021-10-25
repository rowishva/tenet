package com.tenet.web.rest.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.tenet.web.rest.common.entity.MassBookingCategory;
import com.tenet.web.rest.common.entity.MassTime;

@Repository
public interface MassBookingCategoryRepository
		extends JpaRepository<MassBookingCategory, Long>, JpaSpecificationExecutor<MassBookingCategory> {

	MassBookingCategory findByTagAndMassTime(String tag, MassTime massTime);

	List<MassBookingCategory> findByMassTime(MassTime massTime);

}
