package com.kodilla.ecommercee.dto;

import com.kodilla.ecommercee.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private List<Product> products = new ArrayList<>();
    private UserDto userDto;

    public OrderDto(CartDto cartDto, UserDto userDto) {
        this.cartDto = cartDto;
        this.userDto = userDto;
    }
}
