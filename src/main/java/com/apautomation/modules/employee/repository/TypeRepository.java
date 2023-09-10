package com.apautomation.modules.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apautomation.modules.employee.model.Type;

public interface TypeRepository extends JpaRepository<Type, Long> {
	
}
