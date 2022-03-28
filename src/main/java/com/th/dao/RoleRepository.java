package com.th.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.th.entities.AppRole;

public interface RoleRepository extends JpaRepository<AppRole, String> {
	public AppRole findByRoleName(String roleName);

}
