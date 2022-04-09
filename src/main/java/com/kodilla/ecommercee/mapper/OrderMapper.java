package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.dto.CartDto;
import com.kodilla.ecommercee.dto.OrderDto;
import com.kodilla.ecommercee.dto.UserDto;

public class OrderMapper {
    public static Order mapToOrder(OrderDto orderDto){
        CartDto cartDto = orderDto.getCartDto();
        UserDto userDto = orderDto.getUserDto();
        Order order = new Order(orderDto.getId(), CartMapper.cartDtoToCart(cartDto),
                UserMapper.mapUserDtoToUser(userDto));
        return order;
    }

    public static OrderDto mapToOrderDto(Order order) {
        Cart cart = order.getCart();
        User user = order.getUser();
        OrderDto orderDto = new OrderDto(order.getId(), CartMapper.cartToCartDto(cart),
                UserMapper.mapUserToUserDto(user));
        orderDto.setId(order.getId());
        return orderDto;
    }
}
