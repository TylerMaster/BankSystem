package com.example.banksystem.security.jwt;

import java.io.IOException;
import java.util.Collections;

import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
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

		    Authentication authentication =
		            new UsernamePasswordAuthenticationToken(
		                    JwtUtil.getClaims(token).getSubject(),
		                    null,
		                    Collections.emptyList()
		            );

		    SecurityContextHolder.getContext()
		            .setAuthentication(authentication);
		}

		filterChain.doFilter(request, response);
	}

}
