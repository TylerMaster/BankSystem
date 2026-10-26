package com.example.banksystem.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


@Configuration
public class SecurityConfig {
	
	
	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class).build();		
	}
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	
	 @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)
	            throws Exception {

		 return httpSecurity
					.csrf(csrf -> csrf.disable())
					.authorizeHttpRequests(authorize -> {
						
						authorize.requestMatchers("/api/createnewuser").permitAll();
						
						authorize.anyRequest().authenticated();
					})
					.addFilterBefore(
							new BasicAuthenticationFilter(authenticationManager(httpSecurity)),
							UsernamePasswordAuthenticationFilter.class)
					.build();
	    }
	 
	 
	}