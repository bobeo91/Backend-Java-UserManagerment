package com.npv.form;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NonNull;

@Data
public class GroupFilterForm {
	@NonNull
	private String type;
	
	/*Filter by Date */
	//@NonNull
	//@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date startDate;
	
	//@NonNull
	//@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date endDate;
	
	/*Filter by DateTime */
//	@NonNull
//	private String startDate;
//	@NonNull
//	private String endDate;
	public GroupFilterForm(String type, Date minDate, Date maxDate) {
		this.type = type;
		this.startDate = minDate;
		this.endDate = maxDate;
	}
	
}
