package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.dto.OrderDto;
import com.kodilla.ecommercee.dto.UserDto;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {
    public static Order mapToOrder(OrderDto orderDto){
        UserDto userDto = orderDto.getUserDto();
        Order order = new Order(UserMapper.mapUserDtoToUser(userDto));
        return order;
    }

    public static List<OrderDto> mapToOrderDtoList(List<Order> orders){
        return orders.stream()
                .map(l->mapToOrderDto(l))
                .collect(Collectors.toList());
    }

    public static OrderDto mapToOrderDto(Order order) {
        User user = order.getUser();
        OrderDto orderDto = new OrderDto(UserMapper.mapUserToUserDto(user));
        return orderDto;
    }
}
