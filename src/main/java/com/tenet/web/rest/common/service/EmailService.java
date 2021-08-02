package com.tenet.web.rest.common.service;

import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.tenet.web.rest.common.dto.Email;

import freemarker.template.Configuration;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private Configuration fmConfiguration;

	public EmailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public void sendMail(Email mail) {

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper;
		try {
			mimeMessageHelper = new MimeMessageHelper(mimeMessage, false);
			mimeMessageHelper.setTo(mail.getTo());
			mimeMessageHelper.setFrom(mail.getFrom());
			mimeMessageHelper.setSubject(mail.getSubject());
			mimeMessageHelper.setText(mail.getContent());
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		javaMailSender.send(mimeMessage);
	}

	public void sendEmailWithTemplate(Email mail, int template) {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		try {

			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

			mimeMessageHelper.setSubject(mail.getSubject());
			mimeMessageHelper.setFrom(mail.getFrom());
			mimeMessageHelper.setTo(mail.getTo());
			mail.setContent(getContentFromTemplate(mail.getModel(), template));
			mimeMessageHelper.setText(mail.getContent(), true);

			javaMailSender.send(mimeMessageHelper.getMimeMessage());
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public String getContentFromTemplate(Map<String, Object> model, int template) {
		StringBuffer content = new StringBuffer();
		String templateName = null;
		switch (template) {
		case 1:
			templateName = "profilecreateemail.ftl";
			break;
		case 2:
			templateName = "newotp.ftl";
			break;
		case 3:
			templateName = "forgotpassword.ftl";
			break;
		}
		try {
			content.append(FreeMarkerTemplateUtils.processTemplateIntoString(fmConfiguration.getTemplate(templateName),
					model));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content.toString();
	}
}
