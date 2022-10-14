package com.npv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.npv.entity.Group;
import com.npv.form.GroupFilterForm;
import com.npv.repository.IGroupRepository;
import com.npv.specification.GroupSpecification;

@Service
public class GroupService implements IGroupService{

	@Autowired
	private IGroupRepository gpRepository;
	
	@Override
	public List<Group> getListGroups() {
		return gpRepository.findAll();
	}

	@Override
	public Page<Group> getListGroupsPaging(Pageable pageable, GroupFilterForm groupFilterForm) {
		Specification<Group> where = GroupSpecification.buildWhere(groupFilterForm);
		return gpRepository.findAll(where, pageable);
	}

	@Override
	public void creatingGroup(Group group) {
		gpRepository.save(group);
	}
	
	@Override
	public void updateGroup(Group group) {
		gpRepository.save(group);
	}
	
	@Override
	public void deleteGroup(int id) {
		// TODO Auto-generated method stub
		gpRepository.deleteById(id);
	}

}
