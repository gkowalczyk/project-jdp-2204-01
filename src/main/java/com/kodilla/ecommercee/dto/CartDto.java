package com.kodilla.ecommercee.dto;

import com.kodilla.ecommercee.domain.ProductDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartDto {

    private Long id;
    private Long quantity;
    private OrderDto orderDto;
    private UserDto userDto;
    private ProductDto productDto;

    public CartDto(Long id, Long quantity, OrderDto orderDto, UserDto userDto, ProductDto productDto) {
        this.id = id;
        this.quantity = quantity;
        this.orderDto = orderDto;
        this.userDto = userDto;
        this.productDto = productDto;
    }
}
