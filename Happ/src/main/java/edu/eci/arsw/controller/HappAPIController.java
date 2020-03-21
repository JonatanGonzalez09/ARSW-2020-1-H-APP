package edu.eci.arsw.controller;


import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.arsw.service.AdminService;

import edu.eci.arsw.model.User;

@RestController
@RequestMapping("test")
public class HappAPIController {
	
	private AdminService adminService;

    public HappAPIController(AdminService adminService){
        this.adminService = adminService;
    }
	
	@GetMapping("users")
    public List<User> users(){		
        return this.adminService.getAllUsers();
    }
	
	@GetMapping("loged")
	public User loged() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String tmp = auth.getName();
		return adminService.getUser(tmp);
	}
	
	
}
