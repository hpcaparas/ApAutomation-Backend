package com.apautomation.modules.employee.model;

import org.hibernate.validator.constraints.Range;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "department", 
uniqueConstraints = { 
  @UniqueConstraint(columnNames = "deptId")
})
public class Department {
	
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotBlank
	@Size(max = 50)
	private String deptName;
	
	@NotBlank
	@Size(max = 50)
	private String approverName;
	
	private Long approverId;
	
	@Range(min = 0, max = 4294967298L, message="Character count should be less than 4294967298")
	private Long deptId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public Long getDeptId() {
		return deptId;
	}
	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
	public String getApproverName() {
		return approverName;
	}
	public void setApproverName(String approverName) {
		this.approverName = approverName;
	}
	public Long getApproverId() {
		return approverId;
	}
	public void setApproverId(Long approverId) {
		this.approverId = approverId;
	}
}
