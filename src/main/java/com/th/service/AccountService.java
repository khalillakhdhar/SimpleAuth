package com.th.service;

import com.th.entities.AppRole;
import com.th.entities.AppUser;

public interface AccountService {
	public AppUser saveUser(AppUser appUser);
	public AppRole saveRole(AppRole role);
	public void addRoleToUser(String username, String roleName);
	public AppUser findUserByUserName(String username);
	
}
