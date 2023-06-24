package com.bayd.apautomation.repository;


import com.bayd.apautomation.entity.Approval;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ApprovalRepo extends JpaRepository<Approval, UUID> {

}
