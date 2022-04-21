package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.dto.CartDto;
import org.springframework.stereotype.Service;

@Service
public class CartMapper {

    public Cart mapToCart(CartDto cartDto){

        return new Cart(cartDto.getId(), cartDto.getUser(), cartDto.getProducts());
    }

    public CartDto mapToCartDto(Cart cart) {
        return new CartDto(cart.getCartId(), cart.getUser(),cart.getProducts());
    }
}
