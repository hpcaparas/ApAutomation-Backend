package com.bayd.apautomation.mapper;


import com.bayd.apautomation.dto.DepartmentDTO;
import com.bayd.apautomation.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DepartmentMapper extends com.bayd.apautomation.mapper.Mapper<Department, DepartmentDTO> {
    DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);

//    User convertDTOtoEntity(UserDto userDto);
}
