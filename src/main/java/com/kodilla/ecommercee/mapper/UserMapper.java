package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.dto.UserDto;

public class UserMapper {

    public static User mapUserDtoToUser(UserDto userDto){
        return new User(userDto.getId(),
                userDto.getUserName(),
                userDto.getPersonalKey(),
                userDto.isActive(),
                userDto.getOrderList(),
                userDto.getCart());
    }

    public static UserDto mapUserToUserDto(User user) {

        return new UserDto(user.getId(),
                user.getUserName(),
                user.getPersonalKey(),
                user.isActive(),
                user.getOrderList(),
                user.getCart());
    }
}
