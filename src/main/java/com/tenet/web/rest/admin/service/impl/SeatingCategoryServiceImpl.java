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
import com.tenet.web.rest.admin.service.SeatingCategoryService;
import com.tenet.web.rest.common.ApplicationConstants;
import com.tenet.web.rest.common.dto.response.BaseResponse;
import com.tenet.web.rest.common.entity.SeatingCategory;
import com.tenet.web.rest.common.exception.ResourceNotFoundException;
import com.tenet.web.rest.common.repository.SeatingCategoryRepository;

@Service
public class SeatingCategoryServiceImpl implements SeatingCategoryService {

	private Logger LOGGER = LogManager.getLogger(this.getClass());

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private SeatingCategoryRepository seatingCategoryRepository;

	@Override
	public BaseResponse<AllocationRes> updateSeatingCategory(Long id, AllocationDTO request) {
		LOGGER.debug("Calling SeatingCategoryServiceImpl.updateSeatingCategory()");
		SeatingCategory seatingCategory = seatingCategoryRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(ApplicationConstants.ERROR_MSG_SEATINGCATEGORY_NOT_FOUND + id));

		modelMapper.map(request, seatingCategory);
		seatingCategory = seatingCategoryRepository.save(seatingCategory);
		AllocationRes communityAllocationRes = modelMapper.map(seatingCategory, AllocationRes.class);
		BaseResponse<AllocationRes> response = new BaseResponse<AllocationRes>(HttpStatus.OK.value(),
				ApplicationConstants.SUCCESS, communityAllocationRes);
		return response;
	}

	@Override
	public BaseResponse<AllocationRes> getAllSeatingCategory() {
		LOGGER.debug("Calling SeatingCategoryServiceImpl.getAllSeatingCategory()");
		List<SeatingCategory> seatingCategoryList = seatingCategoryRepository.findAll();
		int totalCapacity = seatingCategoryRepository.getTotalCapacity();

		List<AllocationRes> allocationResList = seatingCategoryList.stream()
				.map(seatingCategory -> modelMapper.map(seatingCategory, AllocationRes.class))
				.collect(Collectors.toList());
		BaseResponse<AllocationRes> response = new BaseResponse<AllocationRes>(HttpStatus.OK.value(),
				ApplicationConstants.SUCCESS, allocationResList, totalCapacity);
		return response;
	}

}
