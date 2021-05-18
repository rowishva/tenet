package com.tenet.web.rest.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tenet.web.rest.common.entity.AdminUser;

public interface AdminUserRepository extends JpaRepository<AdminUser, Long> {

}
