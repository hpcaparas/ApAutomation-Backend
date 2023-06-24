package com.apautomation.modules.reimbursement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
public class ReimbursementInfo {
	
	@Id
	@GeneratedValue
	private Long reimbId;
	private String empName;
	private String store;
	private String type;
	private double priceWTax;
	private double tax;
	private String remarks;
	
	public Long getReimbId() {
		return reimbId;
	}
	public void setReimbId(Long reimbId) {
		this.reimbId = reimbId;
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
}
