package com.kodilla.ecommercee.mapper;


import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class UserMapper {

    public User mapToUser(final UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getUserName(),
                userDto.getPersonalKey(),
                userDto.isActive(),
                userDto.getOrderList(),
                userDto.getCart()
        );
    }

    public UserDto mapToUserDto(final User user) {
        return new UserDto(
                user.getId(),
                user.getUserName(),
                user.getPersonalKey(),
                user.isActive(),
                user.getOrderList(),
                user.getCart()
        );
    }
  }