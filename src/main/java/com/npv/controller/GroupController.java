package com.npv.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.npv.dto.GroupDTO;
import com.npv.entity.Group;
import com.npv.form.CreatingFormGroup;
import com.npv.form.GroupFilterForm;
import com.npv.service.GroupService;

@RestController
@RequestMapping(value = "api/groups")
public class GroupController {
	@Autowired
	private GroupService gpService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping()
	public List<GroupDTO> getListGroups() {
		List<Group> groups = gpService.getListGroups();
		
		//List<GroupDTO> lsGrDTO = new ArrayList<GroupDTO>();
		
//		for(Group group : groups) {
//			GroupDTO grDTO = modelMapper.map(group, GroupDTO.class);
//			System.out.println(grDTO);
//			lsGrDTO.add(grDTO);
//		}
		List<GroupDTO> lsGrDTO = modelMapper.map(groups, new TypeToken< List<GroupDTO> >() {}.getType());
		return lsGrDTO;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/paging")
	public Page<GroupDTO> getListGroupsPaging(
			Pageable pageable,
			@RequestParam (name = "type", required = false) String type,
			@RequestParam(name = "startDate", required = false) String startDate, 
			@RequestParam(name = "endDate", required = false) String endDate
	) throws ParseException {
		/*Filter by Date*/
		System.out.println(startDate);
		System.out.println(endDate);
		Date minDate = null, maxDate = null;
		SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
		if (startDate != null && !startDate.equals("null")) {
			minDate = dt.parse(startDate);
		}
		if (endDate != null && !endDate.equals("null")) {
			maxDate = dt.parse(endDate);
		}
		GroupFilterForm gFF = new GroupFilterForm(type, minDate, maxDate);
		
		/*Filter by DateTime*/
		//GroupFilterForm gFF = new GroupFilterForm(type, startDate, endDate);
		
		Page<Group> pageGroup = gpService.getListGroupsPaging(pageable, gFF);
		
		List<GroupDTO> lsGDTO = modelMapper.map(pageGroup.getContent(), new TypeToken< List<GroupDTO> >() {}.getType());
		
		Page<GroupDTO> pGDTO = new PageImpl(lsGDTO, pageable, pageGroup.getTotalElements());
		
		return pGDTO;
	}
	
	@PostMapping
	public ResponseEntity<?> creatingGroup(@RequestBody @Valid CreatingFormGroup cFG) {
		System.out.println("Create group: ");
		System.out.println(cFG.toString());
		
		Group group = modelMapper.map(cFG, Group.class);
		gpService.creatingGroup(group);
		return ResponseEntity.ok().body("Created group successfully!");
	}
	
	@PutMapping
	public ResponseEntity<?> updateGroup(@RequestParam(name = "id") int id, @RequestBody @Valid CreatingFormGroup cFG) {
		System.out.println("Update group: ");
		System.out.println(cFG.toString());
		
		Group group = modelMapper.map(cFG, Group.class);
		group.setId(id);
		
		System.out.println("Update group: ");
		System.out.println(group.toString());
		
		gpService.updateGroup(group);
		return ResponseEntity.ok().body("Updated group successfully!");
	}
	
	@DeleteMapping
	public ResponseEntity<?> deleteGroup(@RequestParam(name = "id") int id) {
		gpService.deleteGroup(id);
		return ResponseEntity.ok().body("Group Deleted");
	}
}
