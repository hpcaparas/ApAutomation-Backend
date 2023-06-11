package com.apautomation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apautomation.model.Employee;

public interface UserRepository extends JpaRepository<Employee, Long> {
	
}
