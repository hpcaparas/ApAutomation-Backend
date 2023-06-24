package com.bayd.apautomation.mapper;

import com.bayd.apautomation.dto.DepartmentDTO;
import com.bayd.apautomation.entity.Department;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-14T12:40:17-0400",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 1.4.300.v20221108-0856, environment: Java 17.0.5 (Eclipse Adoptium)"
)
public class DepartmentMapperImpl implements DepartmentMapper {

    @Override
    public Department convertEntity(DepartmentDTO s) {
        if ( s == null ) {
            return null;
        }

        Department department = new Department();

        return department;
    }

    @Override
    public DepartmentDTO convertDto(Department t) {
        if ( t == null ) {
            return null;
        }

        DepartmentDTO departmentDTO = new DepartmentDTO();

        return departmentDTO;
    }

    @Override
    public List<DepartmentDTO> convertDtos(List<Department> tList) {
        if ( tList == null ) {
            return null;
        }

        List<DepartmentDTO> list = new ArrayList<DepartmentDTO>( tList.size() );
        for ( Department department : tList ) {
            list.add( convertDto( department ) );
        }

        return list;
    }

    @Override
    public List<Department> convertEntities(List<DepartmentDTO> sList) {
        if ( sList == null ) {
            return null;
        }

        List<Department> list = new ArrayList<Department>( sList.size() );
        for ( DepartmentDTO departmentDTO : sList ) {
            list.add( convertEntity( departmentDTO ) );
        }

        return list;
    }
}
