package com.tenet.web.rest.common.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

@Entity
@Table(name = "admin_user")
@Where(clause = "is_deleted=0")
public class AdminUser extends BaseDomain {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id = 0;

	@Column(name = "user_name", length=12, nullable=false)
	private String username = "";

	@Column(name = "first_name", length=50, nullable=false)
	private String firstName = "";

	@Column(name = "last_name", length=50, nullable=false)
	private String lastName = "";

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "role_id")
	private AdminUserRole roleId = null;

	@Column(name = "email", length=30, nullable=false)
	private String email = "";

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public AdminUserRole getRoleId() {
		return roleId;
	}

	public void setRoleId(AdminUserRole roleId) {
		this.roleId = roleId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AdminUser [id=");
		builder.append(id);
		builder.append(", username=");
		builder.append(username);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", roleId=");
		builder.append(roleId);
		builder.append(", email=");
		builder.append(email);
		builder.append("]");
		return builder.toString();
	}

}
