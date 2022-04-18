package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.dto.OrderDto;
import com.kodilla.ecommercee.dto.UserDto;

import java.util.List;

public class OrderMapper {
    public static Order mapToOrder(OrderDto orderDto){
        UserDto userDto = orderDto.getUserDto();
        Order order = new Order(orderDto.getId(), orderDto.getProducts(),
                UserMapper.mapUserDtoToUser(userDto));
        return order;
    }

    public static OrderDto mapToOrderDto(Order order) {
        List<Product> products = order.getProducts();
        User user = order.getUser();
        OrderDto orderDto = new OrderDto(order.getId(), products,
                UserMapper.mapUserToUserDto(user));
        orderDto.setId(order.getId());
        return orderDto;
    }
}
