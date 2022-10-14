package com.npv.specification;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.npv.entity.Group;

import lombok.Data;
import lombok.NonNull;

@SuppressWarnings("serial")
@Data
public class CustomGroupSpecification implements Specification<Group>{

	@NonNull
	private String field;
	
	@NonNull
	private Object value;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Predicate toPredicate(Root<Group> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		
		if (field.equalsIgnoreCase("type")) {
			return criteriaBuilder.equal(root.get("type"), Group.GroupType.toEnum(value.toString()));
		}
		
		/*Filter by Date*/
		if (field.equalsIgnoreCase("minDate")) {
			   Expression es = root.<Date>get("createdAt");
			   return criteriaBuilder.greaterThanOrEqualTo(es, (Date)value);
		}else if (field.equalsIgnoreCase("maxDate")) {
			   Expression es = root.<Date>get("createdAt");
			   return criteriaBuilder.lessThanOrEqualTo(es, (Date)value);
		}
		
		/*Filter by DateTime*/
//		else if (field.equalsIgnoreCase("minDate")) {
//			   Expression es = root.<Instant>get("createdAt");
//			   
//			   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//			   
//			   System.out.println(value.toString().trim());
//			   
//			   LocalDateTime date1 = LocalDateTime.parse(value.toString(), dtf);
//			   
//			   Instant instant1 = date1.toInstant(ZoneOffset.UTC);
//			   
//			   return criteriaBuilder.greaterThanOrEqualTo(es, instant1);
//			
//		}else if (field.equalsIgnoreCase("maxDate")) {
//			   Expression es = root.<Instant>get("createdAt");
//				
//			   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//				   
//			   System.out.println(value.toString().trim());
//				   
//			   LocalDateTime date2 = LocalDateTime.parse(value.toString(), dtf);
//				   
//			   Instant instant2 = date2.toInstant(ZoneOffset.UTC);
//			   return criteriaBuilder.lessThanOrEqualTo(es, instant2);
//		}
		
		return null;
	}

}
