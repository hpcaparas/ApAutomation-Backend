package com.bayd.apautomation.service;

import com.bayd.apautomation.dto.UserDto;

import java.util.Optional;
import java.util.UUID;

public interface UserService extends AbstractService<UserDto, UUID> {
    Optional<UserDto> login(UserDto userDto);

}
