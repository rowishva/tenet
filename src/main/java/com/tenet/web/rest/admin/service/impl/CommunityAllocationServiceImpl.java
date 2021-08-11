package com.tenet.web.rest.admin.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.tenet.web.rest.admin.dto.CommunityAllocationDTO;
import com.tenet.web.rest.admin.dto.CommunityAllocationRes;
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
	public BaseResponse<CommunityAllocationRes> updateCommunityAllocation(Long id, CommunityAllocationDTO request) {
		LOGGER.debug("Calling CommunityAllocationServiceImpl.updateCommunityAllocation()");
		CommunityAllocation communityAllocation = communityAllocationRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(ApplicationConstants.ERROR_MSG_COMMUNITYALLOCATION_NOT_FOUND + id));

		modelMapper.map(request, communityAllocation);
		communityAllocation = communityAllocationRepository.save(communityAllocation);
		CommunityAllocationRes communityAllocationRes = modelMapper.map(communityAllocation,
				CommunityAllocationRes.class);
		BaseResponse<CommunityAllocationRes> response = new BaseResponse<CommunityAllocationRes>(HttpStatus.OK.value(),
				ApplicationConstants.SUCCESS, communityAllocationRes);
		return response;
	}

	@Override
	public BaseResponse<CommunityAllocationRes> getAllCommunityAllocation() {
		LOGGER.debug("Calling CommunityAllocationServiceImpl.getAllCommunityAllocation()");
		List<CommunityAllocation> communityAllocationList = communityAllocationRepository.findAll();

		List<CommunityAllocationRes> communityAllocationResList = communityAllocationList.stream()
				.map(communityAllocation -> modelMapper.map(communityAllocation, CommunityAllocationRes.class))
				.collect(Collectors.toList());
		BaseResponse<CommunityAllocationRes> response = new BaseResponse<CommunityAllocationRes>(HttpStatus.OK.value(),
				ApplicationConstants.SUCCESS, communityAllocationResList);
		return response;
	}

}
