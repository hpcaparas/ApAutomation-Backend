package com.bayd.apautomation.repository;


import com.bayd.apautomation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepo extends JpaRepository<User, UUID> {
    //Optional<User> findByUserName(String username);
    Optional<User> findByEmail(String email);


}
