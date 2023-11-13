package com.bellasolutions.petclinic.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	private String username;
	private String password;
	
	// GETTERS & SETTERS
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	// Parameterized Constructor
	public User(int id, String name, String username, String password) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
	}
	
	// Empty Constructor
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	// ToString Method
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", username=" + username + ", password=" + password + "]";
	}
	
	

}
