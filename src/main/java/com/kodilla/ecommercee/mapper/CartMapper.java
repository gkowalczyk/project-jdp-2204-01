package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.dto.CartDto;

public class CartMapper {

    public static Cart cartDtoToCart(CartDto cartDto){
        return new Cart(cartDto.getId(),cartDto.getProducts());
    }

    public static CartDto cartToCartDto(Cart cart) {
        return new CartDto(cart.getId(),cart.getProducts());
    }
}
