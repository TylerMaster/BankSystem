package com.example.banksystem.security.jwt;

import java.io.IOException;
import java.util.Collections;

import org.springframework.web.filter.OncePerRequestFilter;

import com.example.banksystem.security.services.CustomerDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	 private final CustomerDetailsService customerDetailsService;
	    
	    public JwtAuthenticationFilter(
	            CustomerDetailsService customerDetailsService) {
	        this.customerDetailsService = customerDetailsService;
	    }
	    
	@Override
	protected void doFilterInternal(HttpServletRequest request,
									HttpServletResponse response,
									FilterChain filterChain)
			throws ServletException, IOException{
										
	   
	    
		String authHeader = request.getHeader("Authorization");
		
	

		if (authHeader != null && authHeader.startsWith("Bearer ")) {
		    String token = authHeader.substring(7);

		    if (!JwtUtil.isTokenValid(token)) {
		    	
		    	
		        SecurityContextHolder.clearContext();
		        response.sendError(
		                HttpServletResponse.SC_UNAUTHORIZED,
		                "Invalid or expired token"
		        );
		
		        return;
		    }
		    
		    String email = JwtUtil.getClaims(token).getSubject();

            try {
                UserDetails userDetails =
                    customerDetailsService.loadUserByUsername(email);

                if (!userDetails.isEnabled()) {
                	SecurityContextHolder.clearContext();
                    response.setStatus(
                        HttpServletResponse.SC_UNAUTHORIZED
                    );
                    return;
                }
		    

		    Authentication authentication =
		            new UsernamePasswordAuthenticationToken(
		                    userDetails,
		                    null,
		                    userDetails.getAuthorities()
		            );

		    SecurityContextHolder.getContext()
		            .setAuthentication(authentication);
		    
            }catch (UsernameNotFoundException e) {
                SecurityContextHolder.clearContext();
                response.setStatus(
                    HttpServletResponse.SC_UNAUTHORIZED
                );
                return;
            }
        }
		

		filterChain.doFilter(request, response);
	}

}
