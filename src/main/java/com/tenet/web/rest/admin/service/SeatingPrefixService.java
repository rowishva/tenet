package com.tenet.web.rest.admin.service;

import com.tenet.web.rest.admin.dto.SeatingPrefixDTO;
import com.tenet.web.rest.admin.dto.SeatingPrefixRes;
import com.tenet.web.rest.common.dto.response.BaseResponse;

public interface SeatingPrefixService {

	public BaseResponse<SeatingPrefixRes> updateSeatingPrefix(Long id, SeatingPrefixDTO request);

	public BaseResponse<SeatingPrefixRes> getAllSeatingPrefix();
}
