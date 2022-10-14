package com.npv.dto;

import java.time.Instant;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class GroupDTO {
	private int id;
	
	private String name;
	
	private String type;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date createdAt;
	
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
//	private String createdAt;
	
	private int totalMember;
}
