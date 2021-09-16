package com.tenet.web.rest.admin.service.impl;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenet.web.rest.admin.service.AutoPopulateService;
import com.tenet.web.rest.common.entity.CommunityAllocation;
import com.tenet.web.rest.common.entity.GlobalParameter;
import com.tenet.web.rest.common.entity.SeatingCategory;
import com.tenet.web.rest.common.entity.SeatingPrefix;
import com.tenet.web.rest.common.repository.CommunityAllocationRepository;
import com.tenet.web.rest.common.repository.GlobalParameterRepository;
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

	private static List<String> MANDATORY_LIST = Arrays
			.asList(new String[] { "HM", "RT", "LEC", "EMOHC", "SAN", "MM", "AV", "MC" });

	@Override
	public void init() {
		LOGGER.debug("Calling AutoPopulateServiceImpl.initSeatingCategory()");

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
			SeatingCategory seatingCategory = seatingCategoryRepository.findByCode(key);
			seatingCategory.setTotalAllocation(entry.getValue());
			seatingCategoryRepository.save(seatingCategory);
			if (MANDATORY_LIST.contains(key)) {
				mandatoryCount += entry.getValue();
			}
		}

		LOGGER.debug("Calling AutoPopulateServiceImpl.initCommunityAllocation()");
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

}
