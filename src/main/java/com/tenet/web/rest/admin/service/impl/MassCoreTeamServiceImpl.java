package com.tenet.web.rest.admin.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.tenet.web.rest.admin.service.MassCoreTeamService;
import com.tenet.web.rest.common.ApplicationConstants;
import com.tenet.web.rest.common.dto.response.BaseResponse;
import com.tenet.web.rest.common.dto.response.BaseResponsePage;
import com.tenet.web.rest.common.entity.GlobalParameter;
import com.tenet.web.rest.common.entity.MassBooking;
import com.tenet.web.rest.common.entity.MassBookingCategory;
import com.tenet.web.rest.common.entity.MassTime;
import com.tenet.web.rest.common.exception.ResourceNotFoundException;
import com.tenet.web.rest.common.repository.GlobalParameterRepository;
import com.tenet.web.rest.common.repository.MassBookingCategoryRepository;
import com.tenet.web.rest.common.repository.MassBookingRepository;
import com.tenet.web.rest.common.repository.MassTimeRepository;
import com.tenet.web.rest.common.specification.CoreTeamSerachSpec;
import com.tenet.web.rest.profile.dto.MassCoreTeamDTO;

@Service
public class MassCoreTeamServiceImpl implements MassCoreTeamService {

	private Logger LOGGER = LogManager.getLogger(this.getClass());

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private MassTimeRepository massTimeRepository;

	@Autowired
	private MassBookingCategoryRepository massBookingCategoryRepository;

	@Autowired
	private GlobalParameterRepository globalParameterRepository;

	@Autowired
	private MassBookingRepository massBookingRepository;

	@Override
	@Transactional
	public BaseResponse<MassCoreTeamDTO> createMassCoreTeam(Long massTimeId, MassCoreTeamDTO request) {
		LOGGER.debug("Calling MassCoreTeamServiceImpl.createMassCoreTeam()");
		final MassTime massTime = massTimeRepository.findById(massTimeId).orElseThrow(
				() -> new ResourceNotFoundException(ApplicationConstants.ERROR_MSG_MASSTIME_NOT_FOUND + massTimeId));

		MassBookingCategory massBookingCategory = massBookingCategoryRepository.findByTagAndMassTime(request.getTag(),
				massTime);
		int availableCapacity = massBookingCategory.getAvailableCapacity();
		if (!(availableCapacity > 0)) {
			new ResourceNotFoundException(
					ApplicationConstants.ERROR_MSG_FULLY_BOOKED_MASS_TIME_TAG + massTimeId + " " + request.getTag());
		}

		MassBooking massBooking = massBookingRepository.findByMassTimeIdandTagLimit1(massTimeId, request.getTag());
		GlobalParameter globalParameter = globalParameterRepository.findByCode("SEALL");
		if (!"YES".equals(globalParameter.getValue())) {
			massBooking.setSeatingNo(request.getSeatingNo());
		}
		availableCapacity--;
		massBookingCategory.setAvailableCapacity(availableCapacity);
		massBookingCategoryRepository.save(massBookingCategory);

		massBooking.setFullName(request.getFullName());
		massBooking.setContactNumber(request.getContactNumber());
		massBooking.setMassBookingNo(ApplicationConstants.CORE_TEAM);
		massBooking.setBooked(true);
		massBookingRepository.save(massBooking);

		MassCoreTeamDTO massCoreTeamDTO = modelMapper.map(massBooking, MassCoreTeamDTO.class);
		BaseResponse<MassCoreTeamDTO> response = new BaseResponse<MassCoreTeamDTO>(HttpStatus.CREATED.value(),
				ApplicationConstants.SUCCESS, massCoreTeamDTO);
		return response;
	}

	@Override
	public BaseResponse<MassCoreTeamDTO> updateMassCoreTeam(Long massTimeId, Long massCoreTeamId,
			MassCoreTeamDTO request) {
		LOGGER.debug("Calling MassCoreTeamServiceImpl.updateMassCoreTeam()");
		MassBooking massBooking = massBookingRepository.findById(massCoreTeamId)
				.orElseThrow(() -> new ResourceNotFoundException(
						ApplicationConstants.ERROR_MSG_MASSCORETEAM_NOT_FOUND + massCoreTeamId));

		massBooking.setFullName(request.getFullName());
		massBooking.setContactNumber(request.getContactNumber());
		GlobalParameter globalParameter = globalParameterRepository.findByCode("SEALL");
		if (!"YES".equals(globalParameter.getValue())) {
			massBooking.setSeatingNo(request.getSeatingNo());
		}
		massBookingRepository.save(massBooking);
		MassCoreTeamDTO massCoreTeamDTO = modelMapper.map(massBooking, MassCoreTeamDTO.class);
		BaseResponse<MassCoreTeamDTO> response = new BaseResponse<MassCoreTeamDTO>(HttpStatus.OK.value(),
				ApplicationConstants.SUCCESS, massCoreTeamDTO);
		return response;
	}

	@Override
	public BaseResponse<MassCoreTeamDTO> deleteMassCoreTeam(Long massTimeId, Long massCoreTeamId) {
		LOGGER.debug("Calling MassCoreTeamServiceImpl.updateMassCoreTeam()");
		MassBooking massBooking = massBookingRepository.findById(massCoreTeamId)
				.orElseThrow(() -> new ResourceNotFoundException(
						ApplicationConstants.ERROR_MSG_MASSCORETEAM_NOT_FOUND + massCoreTeamId));

		massBooking.setFullName("");
		massBooking.setContactNumber("");
		massBooking.setBooked(false);
		massBookingRepository.save(massBooking);
		BaseResponse<MassCoreTeamDTO> response = new BaseResponse<MassCoreTeamDTO>(HttpStatus.NO_CONTENT.value(),
				ApplicationConstants.SUCCESS);
		return response;
	}

	@Override
	public BaseResponse<MassCoreTeamDTO> getMassCoreTeam(Long massTimeId, Long massCoreTeamId) {
		LOGGER.debug("Calling MassCoreTeamServiceImpl.getAllMassCoreTeam()");
		MassBooking massBooking = massBookingRepository.findById(massCoreTeamId)
				.orElseThrow(() -> new ResourceNotFoundException(
						ApplicationConstants.ERROR_MSG_MASSCORETEAM_NOT_FOUND + massCoreTeamId));
		MassCoreTeamDTO massCoreTeamDTO = modelMapper.map(massBooking, MassCoreTeamDTO.class);
		BaseResponse<MassCoreTeamDTO> response = new BaseResponse<MassCoreTeamDTO>(HttpStatus.OK.value(),
				ApplicationConstants.SUCCESS, massCoreTeamDTO);
		return response;
	}

	@Override
	public BaseResponse<MassCoreTeamDTO> getAllMassCoreTeam(Long massTimeId) {
		LOGGER.debug("Calling MassCoreTeamServiceImpl.getAllMassCoreTeam()");
		List<MassBooking> massBookingList = massBookingRepository.findByCoreTeamList(massTimeId);
		BaseResponse<MassCoreTeamDTO> response = new BaseResponse<MassCoreTeamDTO>(HttpStatus.OK.value(),
				ApplicationConstants.SUCCESS);
		if (massBookingList != null && massBookingList.size() > 0) {
			List<MassCoreTeamDTO> massCoreTeamDTOList = massBookingList.stream()
					.map(massCoreTeam -> modelMapper.map(massCoreTeam, MassCoreTeamDTO.class))
					.collect(Collectors.toList());
			response.setResponseList(massCoreTeamDTOList);
		}
		return response;
	}

	@Override
	public BaseResponsePage<MassCoreTeamDTO> searchMassCoreTeam(CoreTeamSerachSpec spec, Pageable pageable) {
		LOGGER.debug("Calling MassCoreTeamServiceImpl.searchMassCoreTeam()");
		Page<MassBooking> massBookingPage = massBookingRepository.findAll(spec, pageable);
		BaseResponsePage<MassCoreTeamDTO> response = new BaseResponsePage<MassCoreTeamDTO>(HttpStatus.OK.value(),
				ApplicationConstants.SUCCESS);
		if (massBookingPage != null) {
			List<MassCoreTeamDTO> massBookingList = massBookingPage.stream()
					.map(massBooking -> modelMapper.map(massBooking, MassCoreTeamDTO.class))
					.collect(Collectors.toList());
			response.setResponseList(massBookingList);
			response.setTotalElements(massBookingPage.getTotalElements());
			response.setTotalPages(massBookingPage.getTotalPages());
		}
		return response;
	}

}
