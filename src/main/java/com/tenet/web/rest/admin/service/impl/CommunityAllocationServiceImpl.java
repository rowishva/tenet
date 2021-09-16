package com.tenet.web.rest.admin.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.tenet.web.rest.admin.dto.AllocationDTO;
import com.tenet.web.rest.admin.dto.AllocationRes;
import com.tenet.web.rest.admin.service.CommunityAllocationService;
import com.tenet.web.rest.common.ApplicationConstants;
import com.tenet.web.rest.common.dto.response.BaseResponse;
import com.tenet.web.rest.common.entity.CommunityAllocation;
import com.tenet.web.rest.common.exception.ResourceNotFoundException;
import com.tenet.web.rest.common.repository.CommunityAllocationRepository;

@Service
public class CommunityAllocationServiceImpl implements CommunityAllocationService {

	private Logger LOGGER = LogManager.getLogger(this.getClass());

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CommunityAllocationRepository communityAllocationRepository;

	@Override
	public BaseResponse<AllocationRes> updateCommunityAllocation(Long id, AllocationDTO request) {
		LOGGER.debug("Calling CommunityAllocationServiceImpl.updateCommunityAllocation()");
		CommunityAllocation communityAllocation = communityAllocationRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(ApplicationConstants.ERROR_MSG_COMMUNITYALLOCATION_NOT_FOUND + id));

		modelMapper.map(request, communityAllocation);
		communityAllocation = communityAllocationRepository.save(communityAllocation);
		AllocationRes communityAllocationRes = modelMapper.map(communityAllocation, AllocationRes.class);
		BaseResponse<AllocationRes> response = new BaseResponse<AllocationRes>(HttpStatus.OK.value(),
				ApplicationConstants.SUCCESS, communityAllocationRes);
		return response;
	}

	@Override
	public BaseResponse<AllocationRes> getAllCommunityAllocation() {
		LOGGER.debug("Calling CommunityAllocationServiceImpl.getAllCommunityAllocation()");
		List<CommunityAllocation> communityAllocationList = communityAllocationRepository.findAll();
		int totalCapacity = communityAllocationRepository.getTotalCapacity();
		
		List<AllocationRes> allocationResList = communityAllocationList.stream()
				.map(communityAllocation -> modelMapper.map(communityAllocation, AllocationRes.class))
				.collect(Collectors.toList());
		BaseResponse<AllocationRes> response = new BaseResponse<AllocationRes>(HttpStatus.OK.value(),
				ApplicationConstants.SUCCESS, allocationResList, totalCapacity);
		return response;
	}

}
