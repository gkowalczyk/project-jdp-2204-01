package com.kodilla.ecommercee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class OrderDto {
    private Long id;
    private CartDto cartDto;
    private UserDto userDto;
}
