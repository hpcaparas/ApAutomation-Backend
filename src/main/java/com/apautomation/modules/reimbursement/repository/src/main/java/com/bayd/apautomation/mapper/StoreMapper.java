package com.bayd.apautomation.mapper;


import com.bayd.apautomation.dto.StoreDTO;
import com.bayd.apautomation.entity.Store;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StoreMapper extends com.bayd.apautomation.mapper.Mapper<Store, StoreDTO> {
    StoreMapper INSTANCE = Mappers.getMapper(StoreMapper.class);

//    User convertDTOtoEntity(UserDto userDto);
}
