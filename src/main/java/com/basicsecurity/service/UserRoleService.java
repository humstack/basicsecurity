package com.basicsecurity.service;

import com.basicsecurity.model.UserRole;
import com.basicsecurity.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleService {
	
	@Autowired
	private UserRoleRepository roleRepository;
	
	public List<UserRole> findAll() {
		return roleRepository.findAll();
	}
	
	public void addRole(UserRole newRole) {
		String roleName = newRole.getName();
		
		if (roleRepository.findByName(roleName).isPresent()){
			throw new IllegalArgumentException("Role already exists");
		}
		
		roleRepository.save(newRole);
	}
}
