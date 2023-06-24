package com.bayd.apautomation.service.impl;

import com.bayd.apautomation.dto.DepartmentDTO;
import com.bayd.apautomation.dto.DepartmentsDTO;
import com.bayd.apautomation.entity.Department;
import com.bayd.apautomation.enums.Status;
import com.bayd.apautomation.exception.CustomResourceNotFoundException;
import com.bayd.apautomation.mapper.DepartmentMapper;
import com.bayd.apautomation.repository.DepartmentRepo;
import com.bayd.apautomation.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepo departmentRepo;
    private final DepartmentMapper departmentMapper = DepartmentMapper.INSTANCE;

    @Override
    public Optional<DepartmentDTO> save(DepartmentDTO businessDto, UUID userUUID) {

        if (Objects.isNull(businessDto) || Objects.isNull(businessDto.getName())) {
            throw new CustomResourceNotFoundException("Please enter valid Data");
        }
        Department department = departmentMapper.convertEntity(businessDto);

        Optional<Department> byName = departmentRepo.findByName(businessDto.getName());
        if (byName.isPresent()) {
            throw new CustomResourceNotFoundException("Department already exists");
        }

        if (Objects.nonNull(businessDto.getDeptid())) {
            Optional<Department> byId = departmentRepo.findById(businessDto.getDeptid());
            if (byId.isPresent()) {
                department.setDeptid(byId.get().getDeptid());
                departmentMapper.prepareForUpdate(department);
            }
        } else {
            department.setDeptid(UUID.randomUUID());
            departmentMapper.prepareForCreate(department);
        }

        Department save = departmentRepo.save(department);
        return Optional.ofNullable(departmentMapper.convertDto(save));
    }

    @Override
    public Optional<DepartmentDTO> get(UUID uuid) {
        Optional<Department> byId = departmentRepo.findById(uuid);
        return byId.map(departmentMapper::convertDto);
    }

    @Override
    public Status delete(UUID uuid) {
        Optional<Department> byId = departmentRepo.findById(uuid);
        if (byId.isPresent()) {
            byId.get().setIsdeleted(Boolean.TRUE);
            departmentRepo.save(byId.get());
            return Status.SUCCESS;
        }
        return Status.FAILED;

    }

    @Override
    public DepartmentsDTO getAllDepartments() {
        List<Department> all = departmentRepo.findAll();
        DepartmentsDTO departmentsDTO = new DepartmentsDTO();
        departmentsDTO.setDepartmentsDTO(departmentMapper.convertDtos(all));
        return departmentsDTO;
    }
}
