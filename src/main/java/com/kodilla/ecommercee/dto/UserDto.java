package com.kodilla.ecommercee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data

public class UserDto {
    private final  Long id;
    private final String userName;
    private final String personalKey;
    private CartsDto cartDto;
    private final boolean isActive;
}
