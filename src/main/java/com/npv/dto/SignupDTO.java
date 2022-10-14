package com.npv.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class SignupDTO {
	@NotBlank(message = "Username can not be null")
	private String username;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String password;
	
	private String role;
}
