package com.machao.base.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.machao.base.model.persit.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {
	
}