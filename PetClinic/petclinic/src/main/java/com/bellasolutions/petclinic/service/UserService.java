package com.bellasolutions.petclinic.service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bellasolutions.petclinic.configuration.MyUserDetails;
import com.bellasolutions.petclinic.entity.User;
import com.bellasolutions.petclinic.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	UserRepository userRep;
	
	@Bean
	public PasswordEncoder password() {
		return new BCryptPasswordEncoder();
	}
	
	
	// Create New User with Username & Password Hashed
	public String createUser(User user) {
		Optional<User> result = userRep.findByUsername(user.getUsername());
		if (result.isPresent()) {
			return "Username must be Unique";
		} else {
			user.setPassword(password().encode(user.getPassword()));     // Convert PW to Hash
			userRep.save(user);
			return "User Created Successfully";
		}
	}
	
	public String signIn(User user) {
		Optional<User> result = userRep.findByUsername(user.getUsername());
		System.out.println(result.toString());
		if (result.isPresent()) {
			User dbUser = result.get();
			boolean flag = password().matches(user.getPassword(), dbUser.getPassword());
			if (flag) {
				return "Success";
			} else {
				return "Invalid Password";
			}
		} else {
			return "Invalid Username";
		}
	}
	
	public List<User> getAllUsers() {
		return userRep.findAll();
	}
	
	public String deleteUserByUsername(String username) {
		Optional<User> result = userRep.findByUsername(username);
		if (result.isEmpty()) {
			return "Username Not Found";
		} else {
			userRep.delete(result.get());
			return username + " Successfully Deleted";
		}
	}
	
	// Users Can Only Update Their Own Passwords by Implementing Principal which Holds the info of Current Authenticated User
	public String changePassword(User user, Principal principal) {
		Optional<User> result = userRep.findByUsernameAndOwner(user.getUsername(), principal.getName());
		if (result.isPresent()) {
			User newUser = result.get();
			newUser.setPassword(password().encode(user.getPassword()));
			userRep.saveAndFlush(newUser);
			return "Password Changed";
		} else {
		return "User Not Found";
		}
	}

	// Admin Can Change Anyone's Password
	public String adminChangePassword(User user) {
		Optional<User> result = userRep.findByUsername(user.getUsername());
		if (result.isPresent()) {
			User newUser = result.get();
			newUser.setPassword(password().encode(user.getPassword()));
			userRep.saveAndFlush(newUser);
			return "Password Changed";
		} else {
		return "User Not Found";
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> resultUser = userRep.findByUsername(username);
		
		resultUser.orElseThrow(() -> new UsernameNotFoundException("Not Found: "+ username));
		
		return resultUser.map(MyUserDetails::new).get();
	}
	
}
