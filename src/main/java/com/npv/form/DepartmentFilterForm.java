package com.npv.form;

import java.util.Date;

import javax.persistence.Convert;

import org.springframework.format.annotation.DateTimeFormat;

import com.npv.utils.MyDateAttributeConverter;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class DepartmentFilterForm {
	@DateTimeFormat(pattern="MM/dd/yyyy")
	@Convert(converter=MyDateAttributeConverter.class)
	private Date minDate;
	
	@DateTimeFormat(pattern="MM/dd/yyyy")
	@Convert(converter=MyDateAttributeConverter.class)
	private Date maxDate;
	
	private String type;
}
