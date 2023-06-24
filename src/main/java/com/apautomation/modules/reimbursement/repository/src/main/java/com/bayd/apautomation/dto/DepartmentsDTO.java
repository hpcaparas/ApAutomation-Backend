package com.bayd.apautomation.dto;

import lombok.Data;

import java.util.List;

@Data
public class DepartmentsDTO {
    private int page;
    private int limit;
    private long total;
    private List<DepartmentDTO> departmentsDTO;
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<DepartmentDTO> getDepartmentsDTO() {
		return departmentsDTO;
	}
	public void setDepartmentsDTO(List<DepartmentDTO> departmentsDTO) {
		this.departmentsDTO = departmentsDTO;
	}
}
