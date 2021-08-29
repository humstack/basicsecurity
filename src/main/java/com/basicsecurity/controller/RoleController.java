package com.basicsecurity.controller;

import com.basicsecurity.model.UserRole;
import com.basicsecurity.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RoleController {
	
	@Autowired
	private UserRoleService roleService;
	
	@GetMapping("/role")
	public String getRoles(Model model){
		model.addAttribute("roles", roleService.findAll());
		
		return "view-roles";
	}
	
	@GetMapping("/role/add")
	public String addRole(Model model){
		model.addAttribute("roles", roleService.findAll());
		
		model.addAttribute("newRole", new UserRole());
		
		return "add-role";
	}
	
	@PostMapping("/role/add")
	public String addRole(@Valid @ModelAttribute("newRole") UserRole newRole, BindingResult result, Model model){
		if (result.hasErrors()){
			return "add-role";
		}
		
		try{
			roleService.addRole(newRole);
		}catch (IllegalArgumentException exception){
			model.addAttribute("errors", exception.getMessage());
			return "add-role";
		}
		
		return "redirect:/";
	}
	
	
}
