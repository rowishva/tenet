package com.tenet.web.rest.common.dto;

import java.io.Serializable;
import java.util.Map;

public class Email implements Serializable {

	private static final long serialVersionUID = 1L;

	private String to;
	private String from;
	private String subject;
	private String content;
	private Map<String, Object> model;

	public Email(String to, String from, String subject, String content, Map<String, Object> model) {
		super();
		this.to = to;
		this.from = from;
		this.subject = subject;
		this.content = content;
		this.model = model;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public Map<String, Object> getModel() {
		return model;
	}

	public void setModel(Map<String, Object> model) {
		this.model = model;
	}

}
