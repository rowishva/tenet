package com.tenet.web.rest.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tenet.web.rest.common.entity.AdminUserRole;

@Repository
public interface AdminUserRoleRepository extends JpaRepository<AdminUserRole, Long> {

}
