package com.kodilla.ecommercee.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private CartDto cartDto;
    private UserDto userDto;


}

