package com.bayd.apautomation.mapper;


import com.bayd.apautomation.dto.ApprovalDTO;
import com.bayd.apautomation.dto.BillDTO;
import com.bayd.apautomation.entity.Approval;
import com.bayd.apautomation.entity.Bill;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ApprovalMapper extends com.bayd.apautomation.mapper.Mapper<Approval, ApprovalDTO> {
    ApprovalMapper INSTANCE = Mappers.getMapper(ApprovalMapper.class);

}
