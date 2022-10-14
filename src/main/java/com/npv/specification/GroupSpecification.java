package com.npv.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.npv.entity.Group;
import com.npv.form.GroupFilterForm;

public class GroupSpecification {
	@SuppressWarnings({ "deprecation", "unused" })
	public static Specification<Group> buildWhere(GroupFilterForm gFF) {
		Specification<Group> where = null;
		
		if (gFF != null && !gFF.getType().equals("null")) {
			CustomGroupSpecification type = new CustomGroupSpecification("type", gFF.getType());
			if (where == null) where = type;
			else where = where.and(type);
		}
		
		if (gFF != null && gFF.getStartDate() != null) {
			CustomGroupSpecification minDate = new CustomGroupSpecification("minDate", gFF.getStartDate());
			if (where == null) where = minDate;
			else where = where.and(minDate);
		}
		
		if (gFF != null && gFF.getEndDate() != null) {
			CustomGroupSpecification maxDate = new CustomGroupSpecification("maxDate", gFF.getEndDate());
			if (where == null) where = maxDate;
			else where = where.and(maxDate);
		}
		System.out.println(where);
		return where;
	}
}
