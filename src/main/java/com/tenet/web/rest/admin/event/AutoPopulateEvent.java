package com.tenet.web.rest.admin.event;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.tenet.web.rest.admin.service.AutoPopulateService;

@Component
public class AutoPopulateEvent {

	private Logger LOGGER = LogManager.getLogger(this.getClass());

	@Autowired
	private AutoPopulateService service;

	@EventListener
	public void appReady(ApplicationReadyEvent event) {
		LOGGER.debug("Calling AutoPopulateEvent.appReady()");
		service.init();
	}

}
