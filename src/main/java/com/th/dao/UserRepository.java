package com.th.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.th.entities.AppUser;

public interface UserRepository extends JpaRepository<AppUser, String> {
	public AppUser findByUsername(String username);
	
	}
