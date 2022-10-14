package com.npv.configuration;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint{

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		
		System.out.println("auth entry point: " + authException);
		
		int httpResponseStatus = HttpServletResponse.SC_BAD_REQUEST;
		
		if(authException instanceof UsernameNotFoundException) {
			System.out.println( authException.getMessage());
			httpResponseStatus = HttpServletResponse.SC_NOT_FOUND;
		}else if (authException instanceof BadCredentialsException) {
			httpResponseStatus = HttpServletResponse.SC_UNAUTHORIZED;
		}else if (authException instanceof InsufficientAuthenticationException) {
			httpResponseStatus = HttpServletResponse.SC_UNAUTHORIZED;
		}
		
		response.sendError(httpResponseStatus, authException.getMessage());
	}

}
