package com.tenet.web.rest.admin.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.tenet.web.rest.admin.dto.SeatingPrefixDTO;
import com.tenet.web.rest.admin.dto.SeatingPrefixRes;
import com.tenet.web.rest.admin.service.SeatingPrefixService;
import com.tenet.web.rest.common.ApplicationConstants;
import com.tenet.web.rest.common.dto.response.BaseResponse;
import com.tenet.web.rest.common.entity.SeatingPrefix;
import com.tenet.web.rest.common.exception.ResourceNotFoundException;
import com.tenet.web.rest.common.repository.SeatingPrefixRepository;

@Service
public class SeatingPrefixServiceImpl implements SeatingPrefixService {

	private Logger LOGGER = LogManager.getLogger(this.getClass());

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private SeatingPrefixRepository seatingPrefixRepository;

	@Override
	public BaseResponse<SeatingPrefixRes> updateSeatingPrefix(Long id, SeatingPrefixDTO request) {
		LOGGER.debug("Calling SeatingCategoryServiceImpl.updateSeatingCategory()");
		SeatingPrefix seatingPrefix = seatingPrefixRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(ApplicationConstants.ERROR_MSG_SEATINGPREFIX_NOT_FOUND + id));

		modelMapper.map(request, seatingPrefix);
		seatingPrefix = seatingPrefixRepository.save(seatingPrefix);
		SeatingPrefixRes seatingPrefixRes = modelMapper.map(seatingPrefix, SeatingPrefixRes.class);
		BaseResponse<SeatingPrefixRes> response = new BaseResponse<SeatingPrefixRes>(HttpStatus.OK.value(),
				ApplicationConstants.SUCCESS, seatingPrefixRes);
		return response;
	}

	@Override
	public BaseResponse<SeatingPrefixRes> getAllSeatingPrefix() {
		LOGGER.debug("Calling SeatingCategoryServiceImpl.getAllSeatingCategory()");
		List<SeatingPrefix> seatingPrefixList = seatingPrefixRepository.findAll();
		int totalCapacity = seatingPrefixRepository.getTotalCapacity();

		List<SeatingPrefixRes> seatingPrefixResList = seatingPrefixList.stream()
				.map(seatingPrefix -> modelMapper.map(seatingPrefix, SeatingPrefixRes.class))
				.collect(Collectors.toList());
		BaseResponse<SeatingPrefixRes> response = new BaseResponse<SeatingPrefixRes>(HttpStatus.OK.value(),
				ApplicationConstants.SUCCESS, seatingPrefixResList, totalCapacity);
		return response;
	}

}
