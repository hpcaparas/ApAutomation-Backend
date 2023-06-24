package com.apautomation.modules.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apautomation.modules.employee.model.Employee;

public interface UserRepository extends JpaRepository<Employee, Long> {
	
}
