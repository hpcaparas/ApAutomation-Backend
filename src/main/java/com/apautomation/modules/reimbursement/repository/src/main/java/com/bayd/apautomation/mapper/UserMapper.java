package com.bayd.apautomation.mapper;


import com.bayd.apautomation.dto.UserDto;
import com.bayd.apautomation.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper extends com.bayd.apautomation.mapper.Mapper<User, UserDto> {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
}
