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

import com.example.banksystem.security.jwt.JwtAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;


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
					.exceptionHandling(exception -> exception
					        .authenticationEntryPoint(
					            new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)
					        )
					    )
					.authorizeHttpRequests(authorize -> {
						authorize.requestMatchers("/login").permitAll();
						authorize.requestMatchers("/api/createnewuser").permitAll();
						  authorize.requestMatchers("/error").permitAll();
						
						authorize.anyRequest().authenticated();
					})
					.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
					.build();
	    }
	 
	 @Bean
	 public JwtAuthenticationFilter jwtAuthenticationFilter() {
		 return new JwtAuthenticationFilter();
	 }
	 
	 
	}