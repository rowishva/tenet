package com.tenet.web.rest.common.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tenet.web.rest.common.dto.Email;
import com.tenet.web.rest.common.service.EmailService;
import com.tenet.web.rest.common.util.Utils;

@RestController
public class EmailController {

	@Autowired
	private EmailService emailService;

	@GetMapping(value = "/sendmailwithtemplate")
	public String sendEmailWithTemplate() {

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("name", "Do Chen");
		model.put("otp", Utils.generateOTP(6));
		Email mail = new Email("user@gmail.com", "no-reply@tenet.com", "Welcome to Tenet", null, model);
		emailService.sendEmailWithTemplate(mail, 1);
		return "sendEmailWithTemplate";
	}
}
