package com.bayd.apautomation.mapper;

import com.bayd.apautomation.dto.UserDto;
import com.bayd.apautomation.entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-14T12:40:17-0400",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 1.4.300.v20221108-0856, environment: Java 17.0.5 (Eclipse Adoptium)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public User convertEntity(UserDto s) {
        if ( s == null ) {
            return null;
        }

        User user = new User();

        return user;
    }

    @Override
    public UserDto convertDto(User t) {
        if ( t == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        return userDto;
    }

    @Override
    public List<UserDto> convertDtos(List<User> tList) {
        if ( tList == null ) {
            return null;
        }

        List<UserDto> list = new ArrayList<UserDto>( tList.size() );
        for ( User user : tList ) {
            list.add( convertDto( user ) );
        }

        return list;
    }

    @Override
    public List<User> convertEntities(List<UserDto> sList) {
        if ( sList == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( sList.size() );
        for ( UserDto userDto : sList ) {
            list.add( convertEntity( userDto ) );
        }

        return list;
    }
}
