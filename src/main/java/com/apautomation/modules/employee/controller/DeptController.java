package com.apautomation.modules.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apautomation.modules.employee.exception.NotFoundException;
import com.apautomation.modules.employee.model.Department;
import com.apautomation.modules.employee.repository.DeptRepository;
import com.apautomation.responses.MessageResponse;

//@CrossOrigin(origins = "*", allowedHeaders = "*")
@CrossOrigin("https://apautomation-backend.azurewebsites.net")
@RestController
@RequestMapping("/api/common")
public class DeptController {
	
	@Autowired
	private DeptRepository deptRepository;
	
	@PostMapping("/department")
	ResponseEntity<MessageResponse> newDept(@RequestBody Department newDept) {
		if (deptRepository.existsByDeptId(newDept.getDeptId())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: GL Code already taken"));
		}else {
			deptRepository.save(newDept);
		}
		return ResponseEntity.ok(new MessageResponse("Department registered successfully!"));
	}
	
	@GetMapping("/departments")
	List<Department> getAllUsers(){
		return deptRepository.findAll();
	}
	
	@GetMapping("/department/{id}")
	Department getDeptById(@PathVariable Long id) {
		return deptRepository.findById(id)
				.orElseThrow(()->new NotFoundException(id, "department"));
	}
	
	@PutMapping("/department/{id}")
	Department updateDept(@RequestBody Department newDept, @PathVariable Long id) {
		
		return deptRepository.findById(id)
				.map(dept -> {
					dept.setDeptName(newDept.getDeptName());
					dept.setDeptId(newDept.getDeptId());
					dept.setApproverId(newDept.getApproverId());
					dept.setApproverName(newDept.getApproverName());
					
					return deptRepository.save(dept);
				}).orElseThrow(() -> new NotFoundException(id, "department"));
	}
	
	@DeleteMapping("/department/{id}")
	String deleteDept(@PathVariable Long id) {
		if(!deptRepository.existsById(id)) {
			throw new NotFoundException(id, "department");
		}
		deptRepository.deleteById(id);
		return "Dept with id "+id+" has been deleted.";
	}
}
