package com.npv.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.npv.entity.Group;
import com.npv.form.GroupFilterForm;

public interface IGroupService {
	List<Group> getListGroups();
	Page<Group> getListGroupsPaging(Pageable pageable, GroupFilterForm groupFilterForm);
	void creatingGroup(Group group);
	void updateGroup(Group group);
	void deleteGroup(int id);
}
