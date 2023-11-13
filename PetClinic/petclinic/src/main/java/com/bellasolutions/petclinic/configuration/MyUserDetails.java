package com.bellasolutions.petclinic.configuration;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bellasolutions.petclinic.entity.User;

public class MyUserDetails implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private long id;
	private String name;
	private String userName;
	private String password;
	
	// Constructor to Create UserDetails from User Entity Object
	public MyUserDetails(User u) {
		this.id = u.getId();
		this.name = u.getName();
		this.userName = u.getUsername();
		this.password = u.getPassword();
	}
	
	public MyUserDetails(String userName) {
		this.userName = userName;
	}
	
	// Empty Constructor
	public MyUserDetails() {
		
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (userName.equalsIgnoreCase("admin")) {
			return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
		} else {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
		}
	}

	@Override
	public String getPassword() {
		
		return password;
	}

	@Override
	public String getUsername() {
		
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}
	
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	

}
