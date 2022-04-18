package com.kodilla.ecommercee.user;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserTestSuite {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;

    @Test
    void testPlainUserSave() {
        //Given
        User user = new User(null, "name", "personalKey",
                true, new ArrayList<>(), new Cart());

        //When
        userRepository.save(user);

        //Then
        Long id = user.getId();
        Optional<User> readUser = userRepository.findById(id);
        String expectedUserName = userRepository.findById(id).get().getUserName();
        String expectedPersonalKey = userRepository.findById(id).get().getPersonalKey();

        assertEquals(1, userRepository.count());
        assertTrue(readUser.isPresent());
        assertEquals("name", expectedUserName);
        assertEquals("personalKey", expectedPersonalKey);
        assertTrue(user.isActive());

        //CleanUp
        userRepository.deleteById(id);
    }

    @Test
    void testSaveUserWithCartAndOrder() {
        //Given
        Product product = new Product();
        List<Product> productList = new ArrayList<>();
        productList.add(product);

        Cart cart = new Cart();
        cart.setProducts(productList);

        Order order = new Order();
        order.setProducts(productList);
        List<Order> orderList = new ArrayList<>();
        orderList.add(order);

        User user = new User(null, "name", "personalKey", true, orderList, cart);
        order.setUser(user);

        //When
        User savedUser = userRepository.save(user);
        Long userId = savedUser.getId();
        Cart savedCart = cartRepository.save(cart);
        Long cartId = savedCart.getId();
        Order savedOrder = orderRepository.save(order);
        Long orderId = savedOrder.getId();

        //Then
        Optional <Cart> readCart = cartRepository.findById(cartId);
        Optional <Order> readOrder = orderRepository.findById(orderId);
        assertTrue(readCart.isPresent());
        assertTrue(readOrder.isPresent());
        assertEquals(1, cartRepository.count());
        assertEquals(1, orderRepository.count());
        assertFalse(savedUser.getOrderList().isEmpty());
        assertFalse(savedUser.getOrderList().get(0).getProducts().isEmpty());
        assertFalse(savedUser.getCart().getProducts().isEmpty());

        //CleanUp
        userRepository.deleteById(userId);
    }

}
