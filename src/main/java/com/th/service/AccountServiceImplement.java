package com.th.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.th.dao.RoleRepository;
import com.th.dao.UserRepository;
import com.th.entities.AppRole;
import com.th.entities.AppUser;

@Service
@Transactional
public class AccountServiceImplement implements AccountService {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	UserRepository userRepository;
	@Autowired 
	RoleRepository roleRepository;
	
	@Override
	public AppUser saveUser(AppUser appUser) {
		// TODO Auto-generated method stub
		
		String hashPW=bCryptPasswordEncoder.encode(appUser.getPassword()); //hashPW = le mot de passe crypté
		System.out.print(hashPW);
		appUser.setPassword(hashPW);
		return userRepository.save(appUser);
	}

	@Override
	public AppRole saveRole(AppRole role) {
		// TODO Auto-generated method stub
		return roleRepository.save(role);
	}

	@Override
	public void addRoleToUser(String username, String roleName) {
		// TODO Auto-generated method stub
		AppRole role=roleRepository.findByRoleName(roleName);
		AppUser user=userRepository.findByUsername(username);
		user.getRoles().add(role);
	}

	@Override
	public AppUser findUserByUserName(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(username);
	}

}
