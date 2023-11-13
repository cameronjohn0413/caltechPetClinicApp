package com.bellasolutions.petclinic.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bellasolutions.petclinic.entity.User;
import com.bellasolutions.petclinic.service.UserService;

@RestController
// @CrossOrigin(origins = "http://localhost:83")
@CrossOrigin
@RequestMapping("petclinic/user")
public class UserController {
	
	//  http://localhost:8080/petclinic/user//admin/getAllUsers
	//  http://localhost:8080/petclinic/user/hey
	
	@Autowired
	UserService userSvc;
	
	
	@PostMapping(value = "/admin/newUser", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String newUser(@RequestBody User user) {
		return userSvc.createUser(user);
	}
	
	
	@PostMapping(value = "/signIn", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String signIn(@RequestBody User user, Principal principal) {
		System.out.println("SignIn Called");
		userSvc.signIn(user);
		return principal.getName();
	}
	
	@GetMapping(value = "/authenticate", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Principal authenticate(Principal principal) {
		System.out.println("Authenticate Called");
		
		return principal;
	}
	
	@GetMapping(value = "/admin/getAllUsers", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getAllUsers() {
		System.out.println("GetAllUsers Called");
		return userSvc.getAllUsers();
	}
	
	@GetMapping(value = "/hey")
	public String hello(Principal principal) {
		System.out.println("Hey Endpoint Called");
		return principal.getName();
	}
	
	@DeleteMapping(value = "/admin/deleteUser")
	public String deleteUser(@RequestParam("username") String username) {
		return userSvc.deleteUserByUsername(username);
	}
	
	// Users Can Only Change their Own Passwords by Implementing Principal which is the Authenticated user
	@PutMapping(value = "/updatePassword", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String updatePassword(@RequestBody User user, Principal principal) {
		return userSvc.changePassword(user, principal);
	}
	
	// Admin Can Change Anyone's Password
	@PutMapping(value = "/admin/updatePassword", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String adminUpdatePassword(@RequestBody User user) {
		return userSvc.adminChangePassword(user);
	}

}
