package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.exception.CartNotFoundException;
import com.kodilla.ecommercee.exception.ProductNotFoundException;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.dto.CartDto;
import com.kodilla.ecommercee.dto.ProductDto;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/carts")
@AllArgsConstructor
public class CartController {

    private final CartService cartService;
    private final CartMapper cartMapper;
    private final ProductMapper productMapper;

    @PostMapping(value = "/new/{userId}")
    public ResponseEntity<CartDto> createCart(@PathVariable Long userId) {
        return ResponseEntity.ok(cartMapper.mapToCartDto(cartService.createCart(userId)));
    }

    @GetMapping(value = "{cartId}")
    public ResponseEntity<List<ProductDto>> getProducts(@PathVariable Long cartId) throws CartNotFoundException {
        List<Product> products = cartService.getProducts(cartId);
        return ResponseEntity.ok(productMapper.mapToProductDtoList(products));
    }

    @PostMapping(value ="/addProduct/{cartId}/{productId}")
    public ResponseEntity<Void> addProductToCart(@PathVariable Long cartId, @PathVariable Long productId) throws CartNotFoundException {
        cartService.addProduct(cartId, productId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/deleteProduct/{cartId}/{productId}")
    public ResponseEntity<Boolean> deleteProductFromCart(@PathVariable Long cartId, @PathVariable Long productId) throws CartNotFoundException, ProductNotFoundException {
        boolean removed = cartService.deleteProduct(cartId,productId);
        return ResponseEntity.ok(removed);
    }

    @PostMapping(value = "/order/{cartId}")
    public ResponseEntity<Boolean> createOrder(@PathVariable Long cartId) {
        Boolean orderCreated = cartService.createOrder(cartId);
        return ResponseEntity.ok(orderCreated);
    }
}
