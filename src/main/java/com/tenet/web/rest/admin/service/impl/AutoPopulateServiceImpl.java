package com.tenet.web.rest.admin.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenet.web.rest.admin.service.AutoPopulateService;
import com.tenet.web.rest.common.entity.CommunityAllocation;
import com.tenet.web.rest.common.entity.GlobalParameter;
import com.tenet.web.rest.common.entity.MassBookingCategory;
import com.tenet.web.rest.common.entity.MassTime;
import com.tenet.web.rest.common.entity.SeatingCategory;
import com.tenet.web.rest.common.entity.SeatingPrefix;
import com.tenet.web.rest.common.repository.CommunityAllocationRepository;
import com.tenet.web.rest.common.repository.GlobalParameterRepository;
import com.tenet.web.rest.common.repository.MassBookingCategoryRepository;
import com.tenet.web.rest.common.repository.MassBookingRepository;
import com.tenet.web.rest.common.repository.SeatingCategoryRepository;
import com.tenet.web.rest.common.repository.SeatingPrefixRepository;

@Service
public class AutoPopulateServiceImpl implements AutoPopulateService {

	private Logger LOGGER = LogManager.getLogger(this.getClass());

	@Autowired
	private SeatingPrefixRepository seatingPrefixRepository;

	@Autowired
	private GlobalParameterRepository globalParameterRepository;

	@Autowired
	private SeatingCategoryRepository seatingCategoryRepository;

	@Autowired
	private CommunityAllocationRepository communityAllocationRepository;

	@Autowired
	private MassBookingRepository massBookingRepository;

	@Autowired
	private MassBookingCategoryRepository massBookingCategoryRepository;

	@Autowired
	private ModelMapper modelMapper;

	private static List<String> MANDATORY_LIST = Arrays
			.asList(new String[] { "HM", "RT", "LEC", "EMOHC", "SAN", "MM", "AV", "MC" });

	@Override
	public void init() {
		LOGGER.debug("Calling AutoPopulateServiceImpl.init()-SeatingCategory");

		int totalCapacity = seatingPrefixRepository.getTotalCapacity();
		GlobalParameter globalParameter = globalParameterRepository.findByCode("TC");
		int totalCapacityGp = Integer.parseInt(globalParameter.getValue());
		if (totalCapacity == totalCapacityGp) {

		}

		List<SeatingPrefix> list = seatingPrefixRepository.findAll();
		Map<String, Integer> tagTotalAllocation = list.stream()
				.collect(Collectors.groupingBy(seatingPrefix -> seatingPrefix.getTag(),
						Collectors.summingInt(seatingPrefix -> seatingPrefix.getAllocationCapacity())));

		Iterator<Map.Entry<String, Integer>> iterator = tagTotalAllocation.entrySet().iterator();
		int mandatoryCount = 0;
		while (iterator.hasNext()) {
			Map.Entry<String, Integer> entry = iterator.next();
			String key = entry.getKey();
			LOGGER.debug("Key = " + key + ", Value = " + entry.getValue());
			SeatingCategory seatingCategory = seatingCategoryRepository.findByTag(key);
			seatingCategory.setTotalCapacity(entry.getValue());
			seatingCategoryRepository.save(seatingCategory);
			if (MANDATORY_LIST.contains(key)) {
				mandatoryCount += entry.getValue();
			}
		}

		LOGGER.debug("Calling AutoPopulateServiceImpl.init()-CommunityAllocation");
		int totalNonMandatoryCount = totalCapacity - mandatoryCount;
		int begAndPG = Math.round(totalNonMandatoryCount * 25 / 100);

		CommunityAllocation communityAllocationBec = communityAllocationRepository.findByCode("BEC");
		communityAllocationBec.setTotalAllocation(begAndPG);
		communityAllocationRepository.save(communityAllocationBec);

		CommunityAllocation communityAllocationPg = communityAllocationRepository.findByCode("PG");
		communityAllocationPg.setTotalAllocation(begAndPG);
		communityAllocationRepository.save(communityAllocationPg);

		CommunityAllocation communityAllocationPs = communityAllocationRepository.findByCode("PS");
		int totalPsCount = totalNonMandatoryCount - 2 * begAndPG;
		communityAllocationPs.setTotalAllocation(totalPsCount);
		communityAllocationRepository.save(communityAllocationPs);

		CommunityAllocation communityAllocationCt = communityAllocationRepository.findByCode("CT");
		communityAllocationCt.setTotalAllocation(mandatoryCount);
		communityAllocationRepository.save(communityAllocationCt);

	}

	@Override
	public void initMassBooking(MassTime massTime) {
		LOGGER.debug("Calling AutoPopulateServiceImpl.initMassBooking()");
		/**
		 * List<SeatingPrefix> list = seatingPrefixRepository.findAll();
		 * List<MassBooking> massBookingList = new ArrayList<MassBooking>(); for
		 * (SeatingPrefix seatingPrefix : list) { if
		 * (!MANDATORY_LIST.contains(seatingPrefix.getTag())) { MassBooking massBooking
		 * = modelMapper.map(seatingPrefix, MassBooking.class);
		 * massBooking.setMassTime(massTime); int sNo = seatingPrefix.getStartNo(); int
		 * eNo = seatingPrefix.getEndNo(); for (int i = sNo; i <= eNo; i++) {
		 * MassBooking copy = massBooking; copy.setSeatingNo(i);
		 * massBookingList.add(copy); } } }
		 * massBookingRepository.saveAll(massBookingList);
		 **/
		String returnValue = massBookingRepository.fnt_mass_booking(massTime.getId());
		LOGGER.debug("Calling fnt_mass_booking return : " + returnValue);
		// if(!"DONE".equals(returnValue)) {
		// throw new Exception(ApplicationConstants.ERROR_MSG_ERROR_IN_FUNCTION);
		// }
		LOGGER.debug("Calling AutoPopulateServiceImpl.initMassBooking().initMassBookingCategory");
		List<SeatingCategory> seatingCategoryList = seatingCategoryRepository.findAll();
		List<MassBookingCategory> massBookingCategoryList = new ArrayList<MassBookingCategory>();
		for (SeatingCategory seatingCategory : seatingCategoryList) {
			MassBookingCategory massBookingCategory = modelMapper.map(seatingCategory, MassBookingCategory.class);
			massBookingCategory.setMassTime(massTime);
			massBookingCategory.setAvailableCapacity(seatingCategory.getTotalCapacity());
			if ("PUBLIC".equals(seatingCategory.getTag())) {
				massBookingCategory.setNextAllocationSequence("ML");
			}
			massBookingCategoryList.add(massBookingCategory);
		}
		massBookingCategoryRepository.saveAll(massBookingCategoryList);
		LOGGER.debug("Calling AutoPopulateServiceImpl.initMassBooking().initMassBookingCategory.End");
	}

}
