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

public class ReimbursementInfoWoutImg {

	private Long id;
	private String empName;
	private String username;
	private String store;
	private String type;
	private double priceWTax;
	private double tax;
	private String remarks;
	private String filename;
	private String department;
	private int status;
	private String approver;
	private String approvalHistory;
	private String approvalDates;
	public String statusDesc;
	
	public ReimbursementInfoWoutImg(long id, String empName, double priceWTax, String remaks, String store, double tax, String type, String filename, String department, 
			String approvalDates, String approvalHistory, String approver, int status, String username) {
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
