package com.tenet.web.rest.admin.service;

import com.tenet.web.rest.common.entity.MassTime;

public interface AutoPopulateService {

	public void init();

	public void initMassBooking(MassTime massTime);
}
