package com.bellasolutions.petclinic;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.bellasolutions.petclinic.entity.User;
import com.bellasolutions.petclinic.repository.UserRepository;

import jakarta.annotation.PostConstruct;

@CrossOrigin
@SpringBootApplication(scanBasePackages = "com.bellasolutions.petclinic")
@EntityScan(basePackages = "com.bellasolutions.petclinic.entity")
@EnableJpaRepositories(basePackages = "com.bellasolutions.petclinic.repository")
public class PetclinicApplication {
	
	

	@Autowired
	UserRepository userRep;
	
	
	public PasswordEncoder password() {
		return new BCryptPasswordEncoder();
	}
	
//	  	Create Admin User Programatically
	@PostConstruct
	public void init() {
		User adminUser = new User();
		adminUser.setName("admin");
		adminUser.setUsername("admin");
		adminUser.setPassword(password().encode("admin"));
		userRep.save(adminUser);
		System.out.println("Admin User Created");
	}
	
	
//	@Autowired
//	ClientService cs;
//	
//	@PostConstruct
//	public void clientTest() {
//		System.out.println(cs.getAllClients());
//		List<Client> clientsgot = cs.getAllClients();
//		for (Client c : clientsgot) {
//			System.out.println(c.getPets());
//			System.out.println("ClientTest");
//		}
//	}
	
	public static void main(String[] args) {
		SpringApplication.run(PetclinicApplication.class, args);
		System.out.println("Spring Boot App UP!");
		
		
	}
	

}
