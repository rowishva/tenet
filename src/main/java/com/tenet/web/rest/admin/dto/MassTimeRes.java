package com.tenet.web.rest.admin.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

public class MassTimeRes implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate date;
	@DateTimeFormat(pattern = "KK:mm")
	private LocalTime time;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

}
