package com.bayd.apautomation.repository;


import com.bayd.apautomation.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DepartmentRepo extends JpaRepository<Department, UUID> {
    //Optional<User> findByUserName(String username);
    Optional<Department> findByName(String name);


}
