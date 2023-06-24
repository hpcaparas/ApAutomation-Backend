package com.bayd.apautomation.mapper;

import com.bayd.apautomation.dto.ApprovalDTO;
import com.bayd.apautomation.entity.Approval;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-14T12:40:17-0400",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 1.4.300.v20221108-0856, environment: Java 17.0.5 (Eclipse Adoptium)"
)
public class ApprovalMapperImpl implements ApprovalMapper {

    @Override
    public Approval convertEntity(ApprovalDTO s) {
        if ( s == null ) {
            return null;
        }

        Approval approval = new Approval();

        return approval;
    }

    @Override
    public ApprovalDTO convertDto(Approval t) {
        if ( t == null ) {
            return null;
        }

        ApprovalDTO approvalDTO = new ApprovalDTO();

        return approvalDTO;
    }

    @Override
    public List<ApprovalDTO> convertDtos(List<Approval> tList) {
        if ( tList == null ) {
            return null;
        }

        List<ApprovalDTO> list = new ArrayList<ApprovalDTO>( tList.size() );
        for ( Approval approval : tList ) {
            list.add( convertDto( approval ) );
        }

        return list;
    }

    @Override
    public List<Approval> convertEntities(List<ApprovalDTO> sList) {
        if ( sList == null ) {
            return null;
        }

        List<Approval> list = new ArrayList<Approval>( sList.size() );
        for ( ApprovalDTO approvalDTO : sList ) {
            list.add( convertEntity( approvalDTO ) );
        }

        return list;
    }
}
