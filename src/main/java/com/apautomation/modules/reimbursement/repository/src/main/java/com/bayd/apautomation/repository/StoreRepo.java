package com.bayd.apautomation.repository;


import com.bayd.apautomation.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface StoreRepo extends JpaRepository<Store, UUID> {
    //Optional<User> findByUserName(String username);
    Optional<Store> findByName(String name);


}
