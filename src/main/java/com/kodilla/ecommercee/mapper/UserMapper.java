package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.dto.UserDto;

public class UserMapper {

    public static User mapUserDtoToUser(UserDto userDto){
        return new User(userDto.getId());
    }

    public static UserDto mapUserToUserDto(User user) {
        return new UserDto(user.getId());
    }
}
