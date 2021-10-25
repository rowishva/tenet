package com.tenet.web.rest.common.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import com.tenet.web.rest.common.enums.MassStatus;

@Entity
@Table(name = "trn_mass_time")
@Where(clause = "is_deleted=0")
@SequenceGenerator(name = "mass_time_sequence_generator", sequenceName = "trn_mass_time_sequence", initialValue = 1000, allocationSize = 1)
public class MassTime extends BaseDomain {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mass_time_sequence_generator")
	private Long id;

	@Column(name = "date")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate date;

	@Column(name = "time")
	@DateTimeFormat(pattern = "KK:mm")
	private LocalTime time;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", length = 20)
	private MassStatus status;

	@Column(name = "total_capacity")
	private int totalCapacity;

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

	public MassStatus getStatus() {
		return status;
	}

	public void setStatus(MassStatus status) {
		this.status = status;
	}

	public int getTotalCapacity() {
		return totalCapacity;
	}

	public void setTotalCapacity(int totalCapacity) {
		this.totalCapacity = totalCapacity;
	}

}
