package com.bayd.apautomation.mapper;

import com.bayd.apautomation.dto.StoreDTO;
import com.bayd.apautomation.entity.Store;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-14T12:40:17-0400",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 1.4.300.v20221108-0856, environment: Java 17.0.5 (Eclipse Adoptium)"
)
public class StoreMapperImpl implements StoreMapper {

    @Override
    public Store convertEntity(StoreDTO s) {
        if ( s == null ) {
            return null;
        }

        Store store = new Store();

        return store;
    }

    @Override
    public StoreDTO convertDto(Store t) {
        if ( t == null ) {
            return null;
        }

        StoreDTO storeDTO = new StoreDTO();

        return storeDTO;
    }

    @Override
    public List<StoreDTO> convertDtos(List<Store> tList) {
        if ( tList == null ) {
            return null;
        }

        List<StoreDTO> list = new ArrayList<StoreDTO>( tList.size() );
        for ( Store store : tList ) {
            list.add( convertDto( store ) );
        }

        return list;
    }

    @Override
    public List<Store> convertEntities(List<StoreDTO> sList) {
        if ( sList == null ) {
            return null;
        }

        List<Store> list = new ArrayList<Store>( sList.size() );
        for ( StoreDTO storeDTO : sList ) {
            list.add( convertEntity( storeDTO ) );
        }

        return list;
    }
}
