package com.bellasolutions.petclinic.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import com.bellasolutions.petclinic.service.UserService;

@Configuration
public class SecurityConfig {

	@Autowired
	UserService userSvc;
	
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
         http
                 .authorizeHttpRequests(request -> request
                		 .requestMatchers("/index.html", "/", "/home", "/login").permitAll()
                		 .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                         .requestMatchers("/petclinic/user/admin/**").hasRole("ADMIN")
                         .requestMatchers("/**").hasAnyRole("ADMIN", "USER")
                         .anyRequest().authenticated())
                 .userDetailsService(userSvc)
                 .csrf((csrf) -> csrf
                         .ignoringRequestMatchers("/**")
                         .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
//                         .permitAll())
//                .formLogin(Customizer.withDefaults())			//  Makes Login Page instead of SignIn PopUp
                 .cors(Customizer.withDefaults())
                 .httpBasic(Customizer.withDefaults());
         return http.build();
    }
    
    
    
}
