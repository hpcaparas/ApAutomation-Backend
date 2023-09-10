package com.apautomation.modules.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.apautomation.modules.employee.model.Department;

public interface DeptRepository extends JpaRepository<Department, Long> {
	boolean existsById(Long id);
	boolean existsByDeptId(Long deptId);
	
	@Query("SELECT approverName FROM Department WHERE deptName = :dept")
	String findApproverByDept(String dept);
}
