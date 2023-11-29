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
@Table(name = "type", 
uniqueConstraints = { 
	@UniqueConstraint(columnNames = "typeId")
})
public class Type {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotBlank
	@Size(max = 50)
	private String typeName;
	
	@Range(min = 0, max = 4294967298L, message="Character count should be less than 4294967298")
	private Long typeId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Long getTypeId() {
		return typeId;
	}
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
}
