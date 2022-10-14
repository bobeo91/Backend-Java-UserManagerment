package com.npv.form;

import java.util.Date;

import javax.validation.constraints.NotBlank;


import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class CreatingFormGroup {
	
	@NotBlank(message = "The name cannot be blank")
	private String name;
	
	@NotBlank(message = "The name cannot be blank")
	private String type;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date createdAt;
	
	private int totalMember;
}
