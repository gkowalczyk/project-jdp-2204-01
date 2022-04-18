package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.dto.CartDto;
import com.kodilla.ecommercee.dto.CartProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/carts")
@RequiredArgsConstructor
public class CartController {

    @PostMapping(value = "/new")
    public ResponseEntity<Cart> createCart() {
        //TODO service
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "{userId}")
    public ResponseEntity<CartDto> getCart(@PathVariable Long userId) {
        //TODO service
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<CartDto> updateCart(@RequestBody CartProductDto cartProductDto) {
        //TODO service
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "{productId}")
    public ResponseEntity<Boolean> deleteProductFromCart(@PathVariable Long productId) {
        //TODO service
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/order")
    public ResponseEntity<Boolean> createOrder(@RequestBody CartDto cartDto) {
        //TODO service
        return ResponseEntity.ok().build();
    }
}
