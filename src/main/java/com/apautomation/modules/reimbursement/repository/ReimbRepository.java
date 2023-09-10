package com.apautomation.modules.reimbursement.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.apautomation.modules.reimbursement.model.ReimbursementInfo;
import com.apautomation.modules.reimbursement.model.ReimbursementInfoList;
import com.apautomation.modules.reimbursement.model.ReimbursementInfoWoutImg;


public interface ReimbRepository extends JpaRepository<ReimbursementInfo, Long> {
	@Query("SELECT NEW com.apautomation.modules.reimbursement.model.ReimbursementInfo("
			+ "u.id, u.empName, u.priceWTax, u.remarks, u.store, u.tax, u.type, u.filename, u.department, u.approvalDates, u.approvalHistory, u.approvalRemarks,"
			+ "u.approversHistory, u.approver, u.status, u.username) FROM ReimbursementInfo u")
	 List<ReimbursementInfo> findAllReimbInfo();
	
	@Query("SELECT u FROM ReimbursementInfo u WHERE u.username = :username")
	 List<ReimbursementInfo> findAllByUsername(String username);
	
	@Query("SELECT NEW com.apautomation.modules.reimbursement.model.ReimbursementInfo("
			+ "u.id, u.empName, u.priceWTax, u.remarks, u.store, u.tax, u.type, u.filename, u.department, u.approvalDates, u.approvalHistory, u.approvalRemarks,"
			+ "u.approversHistory, u.approver, u.status, u.username) FROM ReimbursementInfo u WHERE u.status = 1")
	 List<ReimbursementInfo> findAllPendingForApproval();
	
	@Transactional
	@Modifying
	@Query("update ReimbursementInfo u set u.status = :status, u.approvalDates = :approvalDates, u.approvalHistory = :approvalHistory, u.approvalRemarks = :approvalRemarks,"
			+ "u.approversHistory = :approversHistory, u.approver = '' where u.id = :id")
	void updateReimbApprovalInfo(int status, String approvalDates, String approvalHistory, String approvalRemarks, String approversHistory, long id);
}
