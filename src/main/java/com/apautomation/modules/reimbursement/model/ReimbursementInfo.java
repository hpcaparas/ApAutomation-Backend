package com.apautomation.modules.reimbursement.model;


import org.hibernate.annotations.Type;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Builder
@Entity
public class ReimbursementInfo {
	
	@Id
	@GeneratedValue
	private Long id;
	
	//@Column(columnDefinition="varchar(50) default ''")
	private String empName;
	
	//@Column(columnDefinition="varchar(20) default ''")
	private String username;
	
	//@Column(columnDefinition="varchar(50) default ''")
	private String store;
	
	//@Column(columnDefinition="varchar(50) default ''")
	private String type;
	
	private double priceWTax;
	
	private double tax;
	
	//@Column(columnDefinition="varchar(100) default ''")
	private String remarks;
	
	@Lob
	//@Type(type = "org.hibernate.type.ImageType")
	private byte[] imageData;
	
	//@Column(columnDefinition="varchar(50) default ''")
	private String filename;
	
	//@Column(columnDefinition="varchar(50) default ''")
	private String department;
	
	@Transient
	private MultipartFile file;
	
	//@Column(columnDefinition="int default ''")
	private int status;
	
	//@Column(columnDefinition="varchar(50) default ''")
	private String approver;
	
	//@Column(columnDefinition="varchar(50) default ''")
	private String approvalHistory;
	
	//@Column(columnDefinition="varchar(50) default ''")
	private String approvalDates;
	
	//@Column(columnDefinition="varchar(500) default ''")
	private String approvalRemarks;
	
	//@Column(columnDefinition="varchar(50) default ''")
	private String approversHistory;
	
	@Transient
	public String statusDesc;
	
	public ReimbursementInfo() {};
	
	public ReimbursementInfo(long id, String empName, double priceWTax, String remarks, String store, double tax, String type, String filename, String department, 
			String approvalDates, String approvalHistory,String approvalRemarks, String approversHistory, String approver, int status, String username) {
		this.id = id;
		this.empName = empName;
		this.priceWTax = priceWTax;
		this.remarks = remarks;
		this.store = store;
		this.tax = tax;
		this.type = type;
		this.filename = filename;
		this.department = department;
		this.approvalDates = approvalDates;
		this.approvalHistory = approvalHistory;
		this.approvalRemarks = approvalRemarks;
		this.approversHistory = approversHistory;
		this.approver = approver;
		this.status = status;
		this.username = username;
	}
	
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getStore() {
		return store;
	}
	public void setStore(String store) {
		this.store = store;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getPriceWTax() {
		return priceWTax;
	}
	public void setPriceWTax(double priceWTax) {
		this.priceWTax = priceWTax;
	}
	public double getTax() {
		return tax;
	}
	public void setTax(double tax) {
		this.tax = tax;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	public String getApprover() {
		return approver;
	}
	public void setApprover(String approver) {
		this.approver = approver;
	}
	public String getApprovalHistory() {
		return approvalHistory;
	}
	public void setApprovalHistory(String approvalHistory) {
		this.approvalHistory = approvalHistory;
	}
	public String getApprovalDates() {
		return approvalDates;
	}
	public void setApprovalDates(String approvalDates) {
		this.approvalDates = approvalDates;
	}
	public byte[] getImageData() {
		return imageData;
	}
	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getApprovalRemarks() {
		return approvalRemarks;
	}

	public void setApprovalRemarks(String approvalRemarks) {
		this.approvalRemarks = approvalRemarks;
	}

	public String getApproversHistory() {
		return approversHistory;
	}

	public void setApproversHistory(String approversHistory) {
		this.approversHistory = approversHistory;
	}
}
