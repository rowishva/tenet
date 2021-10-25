package com.tenet.web.rest.common.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.context.SecurityContextHolder;

import com.tenet.web.rest.auth.service.AuthUserDetails;

@MappedSuperclass
public class BaseDomain implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "create_time", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createTime;

	@Column(name = "update_time")
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updateTime;

	@Column(name = "created_by_user", nullable = false)
	@CreatedBy
	private String createdByUser = "DUMMY_USER";

	@Column(name = "updated_by_user")
	@LastModifiedBy
	private String updatedByUser = "DUMMY_USER";

	@Column(name = "is_deleted")
	private boolean isDeleted;

	@Version
	@Column(name = "version_number")
	private long versionNumber = 0;

	@PrePersist
	public void onPrePersist() {
		this.createTime = new Date();
		AuthUserDetails userDetails = (AuthUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		this.createdByUser = userDetails.getUsername();
	}

	@PreUpdate
	public void onPreUpdate() {
		this.updateTime = new Date();
		/*
		 * AuthUserDetails userDetails = (AuthUserDetails)
		 * SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
		 * this.updatedByUser = userDetails.getUsername();
		 */
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getCreatedByUser() {
		return createdByUser;
	}

	public void setCreatedByUser(String createdByUser) {
		this.createdByUser = createdByUser;
	}

	public String getUpdatedByUser() {
		return updatedByUser;
	}

	public void setUpdatedByUser(String updatedByUser) {
		this.updatedByUser = updatedByUser;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public long getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(long versionNumber) {
		this.versionNumber = versionNumber;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BaseDomain [createTime=");
		builder.append(createTime);
		builder.append(", updateTime=");
		builder.append(updateTime);
		builder.append(", createdByUser=");
		builder.append(createdByUser);
		builder.append(", updatedByUser=");
		builder.append(updatedByUser);
		builder.append(", isDeleted=");
		builder.append(isDeleted);
		builder.append(", versionNumber=");
		builder.append(versionNumber);
		builder.append("]");
		return builder.toString();
	}

}
