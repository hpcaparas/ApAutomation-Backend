package com.bayd.apautomation.repository;


import com.bayd.apautomation.entity.Bill;
import com.bayd.apautomation.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BillRepo extends JpaRepository<Bill, UUID> {
    //Optional<User> findByUserName(String username);
    Optional<Bill> findByName(String name);


}
