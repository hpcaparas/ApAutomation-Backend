package com.bayd.apautomation.mapper;


import com.bayd.apautomation.dto.BillDTO;
import com.bayd.apautomation.dto.DepartmentDTO;
import com.bayd.apautomation.entity.Bill;
import com.bayd.apautomation.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BillMapper extends com.bayd.apautomation.mapper.Mapper<Bill, BillDTO> {
    BillMapper INSTANCE = Mappers.getMapper(BillMapper.class);

}
