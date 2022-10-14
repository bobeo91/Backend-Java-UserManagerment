package com.npv.dto;


import lombok.Data;

@Data
public class AccountDTO {
	private int id;
	
	private String username;
	
	private String password;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String role;
	
	private String status;
	
	private String avatarUrl;
	
}
