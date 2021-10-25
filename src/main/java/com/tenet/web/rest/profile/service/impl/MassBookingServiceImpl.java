package com.tenet.web.rest.profile.service.impl;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.tenet.web.rest.admin.dto.MassTimeRes;
import com.tenet.web.rest.auth.service.AuthUserDetails;
import com.tenet.web.rest.common.ApplicationConstants;
import com.tenet.web.rest.common.dto.response.BaseResponse;
import com.tenet.web.rest.common.entity.Dependent;
import com.tenet.web.rest.common.entity.MassBooking;
import com.tenet.web.rest.common.entity.MassBookingCategory;
import com.tenet.web.rest.common.entity.MassTime;
import com.tenet.web.rest.common.entity.Profile;
import com.tenet.web.rest.common.exception.ResourceNotFoundException;
import com.tenet.web.rest.common.repository.DependentRepository;
import com.tenet.web.rest.common.repository.MassBookingCategoryRepository;
import com.tenet.web.rest.common.repository.MassBookingRepository;
import com.tenet.web.rest.common.repository.MassTimeRepository;
import com.tenet.web.rest.common.repository.ProfileRepository;
import com.tenet.web.rest.common.util.Utils;
import com.tenet.web.rest.profile.dto.MassBookingDTO;
import com.tenet.web.rest.profile.dto.MassBookingNoDTO;
import com.tenet.web.rest.profile.dto.MassBookingRes;
import com.tenet.web.rest.profile.dto.MassBookingResponse;
import com.tenet.web.rest.profile.service.MassBookingService;

@Service
public class MassBookingServiceImpl implements MassBookingService {

	private Logger LOGGER = LogManager.getLogger(this.getClass());

	@Autowired
	private MassTimeRepository massTimeRepository;

	@Autowired
	private MassBookingRepository massBookingRepository;

	@Autowired
	private ProfileRepository profileRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private DependentRepository dependentRepository;

	@Autowired
	private MassBookingCategoryRepository massBookingCategoryRepository;

	public static Map<String, String> allocationSeq = Map.ofEntries(
			new AbstractMap.SimpleEntry<String, String>("ML", "MR"),
			new AbstractMap.SimpleEntry<String, String>("MR", "SH"),
			new AbstractMap.SimpleEntry<String, String>("SH", "BVM"),
			new AbstractMap.SimpleEntry<String, String>("BVM", "ML"));

	@Transactional
	@Override
	public BaseResponse<MassBookingNoDTO> createMassBooking(MassBookingDTO request, long massTimeId) {

		LOGGER.debug("Calling MassBookingServiceImpl.createMassBooking()");
		final MassTime massTime = massTimeRepository.findById(massTimeId).orElseThrow(
				() -> new ResourceNotFoundException(ApplicationConstants.ERROR_MSG_MASSTIME_NOT_FOUND + massTimeId));

		int bookingCount = 0;
		AuthUserDetails userDetails = (AuthUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		String logInUsername = userDetails.getUsername();
		Profile profile = profileRepository.findByUsername(logInUsername);

		if (request.isProfileBooking()) {
			bookingCount++;
		}
		List<Long> dependents = request.getDependents();
		if (dependents.size() > 0) {
			bookingCount = bookingCount + dependents.size();
		}

		MassBookingCategory massBookingCategory = massBookingCategoryRepository.findByTagAndMassTime("PUBLIC",
				massTime);

		String nextAllocationSequence = massBookingCategory.getNextAllocationSequence();
		int availableCapacity = massBookingCategory.getAvailableCapacity();
		if (bookingCount > availableCapacity) {
			new Exception(ApplicationConstants.ERROR_MSG_NOT_ENOUGH_CAPACITY + availableCapacity);
		}

		List<MassBooking> massBookingList = getListOfSeat(massTimeId, nextAllocationSequence, bookingCount);

		Long nextMassBookingNo = massBookingRepository.getNextMassBookingNo();
		LOGGER.debug("Calling Next Mass Booking No : " + nextMassBookingNo);
		String massBookingNo = Utils.generateMassBookingNo(massTime, nextMassBookingNo);
		LOGGER.debug("Calling Mass Booking No : " + massBookingNo);

		int start = 0;
		if (request.isProfileBooking()) {
			MassBooking massBooking = massBookingList.get(0);
			massBooking.setBooked(true);
			massBooking.setMassBookingNo(massBookingNo);
			massBooking.setProfileId(profile.getId());
			massBooking.setFullName(profile.getFullName());
			massBooking.setContactNumber(profile.getContactNumber());
			start++;
		}
		for (int i = start, j = 0; i <= dependents.size(); i++, j++) {
			MassBooking massBooking = massBookingList.get(i);
			massBooking.setBooked(true);
			massBooking.setMassBookingNo(massBookingNo);
			massBooking.setProfileId(profile.getId());
			Long dependentId = dependents.get(j);
			Dependent dependent = dependentRepository.findById(dependentId)
					.orElseThrow(() -> new ResourceNotFoundException(
							ApplicationConstants.ERROR_MSG_DEPENDENT_NOT_FOUND + dependentId));
			massBooking.setDependentId(dependentId);
			massBooking.setFullName(dependent.getFullName());
			massBooking.setContactNumber(dependent.getContactNumber());
		}

		massBookingList = massBookingRepository.saveAll(massBookingList);

		int newAvailableCapacity = massBookingCategory.getAvailableCapacity() - bookingCount;
		LOGGER.debug("Set new AvailableCapacity : " + newAvailableCapacity);
		massBookingCategory.setAvailableCapacity(newAvailableCapacity);
		massBookingCategory.setNextAllocationSequence(allocationSeq.get(nextAllocationSequence));
		massBookingCategoryRepository.save(massBookingCategory);
		BaseResponse<MassBookingNoDTO> response = new BaseResponse<MassBookingNoDTO>(HttpStatus.CREATED.value(),
				ApplicationConstants.SUCCESS, new MassBookingNoDTO(massBookingNo));
		return response;
	}

	private List<MassBooking> getListOfSeat(long massTimeId, String nextSequence, int bookingCount) {
		List<MassBooking> massBookingList = new ArrayList<MassBooking>();
		boolean condition = true;
		int count = bookingCount;
		String sequence = nextSequence;
		do {
			List<MassBooking> massBooking = massBookingRepository.findByMassBookingList(massTimeId, sequence, "PUBLIC",
					count);
			massBookingList.addAll(massBooking);
			if (massBookingList.size() == bookingCount) {
				condition = false;
			} else {
				count = bookingCount - massBookingList.size();
				sequence = allocationSeq.get(sequence);
			}
		} while (condition);
		return massBookingList;
	}

	@Override
	public BaseResponse<MassBookingNoDTO> deleteMassBooking(long massBookingId) {
		LOGGER.debug("Calling MassBookingServiceImpl.deleteMassBooking()");
		final MassBooking massBooking = massBookingRepository.findById(massBookingId)
				.orElseThrow(() -> new ResourceNotFoundException(
						ApplicationConstants.ERROR_MSG_MASSBOOKING_NOT_FOUND + massBookingId));
		massBooking.setBooked(false);
		massBooking.setMassBookingNo("");
		massBooking.setProfileId(0L);
		massBooking.setFullName("");
		massBooking.setContactNumber("");
		massBooking.setDependentId(0L);
		massBookingRepository.save(massBooking);
		BaseResponse<MassBookingNoDTO> response = new BaseResponse<MassBookingNoDTO>(HttpStatus.OK.value(),
				ApplicationConstants.SUCCESS);
		return response;
	}

	@Override
	public BaseResponse<MassBookingResponse> getAllMassBooking() {
		LOGGER.debug("Calling MassBookingServiceImpl.searchMassBooking()");
		AuthUserDetails userDetails = (AuthUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		String logInUsername = userDetails.getUsername();
		Profile profile = profileRepository.findByUsername(logInUsername);

		List<MassBooking> massBookingList = massBookingRepository.findByProfileId(profile.getId());
		BaseResponse<MassBookingResponse> response = new BaseResponse<MassBookingResponse>(HttpStatus.OK.value(),
				ApplicationConstants.SUCCESS);
		if (massBookingList != null) {
			List<MassBookingResponse> massBookingResponseList = new ArrayList<MassBookingResponse>();
			Map<Long, List<MassBooking>> massBookingMap = massBookingList.stream()
					.collect(Collectors.groupingBy(MassBooking::getMassTimeId));
			Iterator<Map.Entry<Long, List<MassBooking>>> iterator = massBookingMap.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<Long, List<MassBooking>> entry = iterator.next();
				Long massTimeId = entry.getKey();
				List<MassBooking> massBookings = entry.getValue();

				final MassTime massTime = massTimeRepository.findById(massTimeId)
						.orElseThrow(() -> new ResourceNotFoundException(
								ApplicationConstants.ERROR_MSG_MASSTIME_NOT_FOUND + massTimeId));

				MassTimeRes massTimeDTO = modelMapper.map(massTime, MassTimeRes.class);
				String massBookingNo = massBookings.get(0).getMassBookingNo();
				List<MassBookingRes> massBookingResList = massBookings.stream()
						.map(massBooking -> modelMapper.map(massBooking, MassBookingRes.class))
						.collect(Collectors.toList());

				massBookingResponseList.add(new MassBookingResponse(massTimeDTO, massBookingNo, massBookingResList));
			}
			response.setResponseList(massBookingResponseList);
		}
		return response;
	}

}
