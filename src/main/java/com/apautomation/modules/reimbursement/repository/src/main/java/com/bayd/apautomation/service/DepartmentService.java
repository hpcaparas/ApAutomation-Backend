package com.bayd.apautomation.service;

import com.bayd.apautomation.dto.DepartmentDTO;
import com.bayd.apautomation.dto.DepartmentsDTO;

import java.util.UUID;

public interface DepartmentService extends AbstractService<DepartmentDTO, UUID> {

    DepartmentsDTO getAllDepartments();
}
