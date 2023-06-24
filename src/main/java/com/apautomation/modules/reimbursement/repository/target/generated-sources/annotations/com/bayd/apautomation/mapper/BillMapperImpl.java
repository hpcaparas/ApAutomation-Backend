package com.bayd.apautomation.mapper;

import com.bayd.apautomation.dto.BillDTO;
import com.bayd.apautomation.entity.Bill;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-14T12:40:17-0400",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 1.4.300.v20221108-0856, environment: Java 17.0.5 (Eclipse Adoptium)"
)
public class BillMapperImpl implements BillMapper {

    @Override
    public Bill convertEntity(BillDTO s) {
        if ( s == null ) {
            return null;
        }

        Bill bill = new Bill();

        return bill;
    }

    @Override
    public BillDTO convertDto(Bill t) {
        if ( t == null ) {
            return null;
        }

        BillDTO billDTO = new BillDTO();

        return billDTO;
    }

    @Override
    public List<BillDTO> convertDtos(List<Bill> tList) {
        if ( tList == null ) {
            return null;
        }

        List<BillDTO> list = new ArrayList<BillDTO>( tList.size() );
        for ( Bill bill : tList ) {
            list.add( convertDto( bill ) );
        }

        return list;
    }

    @Override
    public List<Bill> convertEntities(List<BillDTO> sList) {
        if ( sList == null ) {
            return null;
        }

        List<Bill> list = new ArrayList<Bill>( sList.size() );
        for ( BillDTO billDTO : sList ) {
            list.add( convertEntity( billDTO ) );
        }

        return list;
    }
}
