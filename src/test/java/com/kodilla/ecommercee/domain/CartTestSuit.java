package com.kodilla.ecommercee.domain;


import com.kodilla.ecommercee.repository.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
@Transactional
public class CartTestSuit {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;

    Product product1;
    Product product2;
    Product product3;
    Cart cart;
    Long userId;

    @Before
    public void beforeEach(){

        User user = new User("Jan Kowalski", "personalKey", true);
        userRepository.save(user);
        userId = user.getId();
        Group jackets = new Group("jackets");
        Group blouses = new Group("blouses");
        jackets = groupRepository.save(jackets);
        blouses = groupRepository.save(blouses);
        product1 = new Product("Denim jacket", "Jacket in sturdy cotton denim with a collar", new BigDecimal(250.99), jackets);
        product2 = new Product("Oversize biker jacket", "Oversize biker jacket with a diagonal zip down ", new BigDecimal(350.99), jackets);
        product3 = new Product("V-neck blouse", "V-neck blouse in softly draping woven fabric", new BigDecimal(60.99), blouses);
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);

        cart = new Cart();
        cart.getProducts().add(product1);
        cart.getProducts().add(product1);
        cart.getProducts().add(product1);
        cart.getProducts().add(product2);
        cartRepository.save(cart);
    }

    @After
    public void cleanUp() {
        orderRepository.deleteAll();
        userRepository.deleteAll();
        cartRepository.deleteAll();
        productRepository.deleteAll();
        groupRepository.deleteAll();
    }

    @Test
    public void testCreateCart() {
        //Given
        User user = userRepository.findById(userId).get();
        user.setCart(cart);
        //When
        int productsQuantity = userRepository.findById(userId).get().getCart().getProducts().size();
        int cartQuantity = cartRepository.findAll().size();
        int product1quantity = userRepository.findById(userId).get().getCart().getProducts().stream()
                .filter(product -> product.equals(product1))
                        .collect(Collectors.toList()).size();
        //Then
        assertEquals(4, productsQuantity);
        assertEquals(1, cartQuantity);
        assertEquals(3, product1quantity);

    }

    @Test
    public void testUpdateCart() {
        //Given
        User user = userRepository.findById(userId).get();
        user.setCart(cart);

        //When
        Cart updateCart = userRepository.findById(userId).get().getCart();
        updateCart.getProducts().add(product3);
        int productsQuantity = userRepository.findById(userId).get().getCart().getProducts().size();

        //Then
        assertEquals(5, productsQuantity);
    }

    @Test
    public void testUserAndProductsStaysWhenRemovedCart() {
        //Given
        User user = userRepository.findById(userId).get();
        user.setCart(cart);
        //When
        cartRepository.delete(cart);
        //Then
        assertTrue(userRepository.findById(userId).isPresent());
        assertNotNull(productRepository.findAll());
    }

    @Test
    public void makeOrderAccordingToCart() {
        //Given
        User user = userRepository.findById(userId).get();
        user.setCart(cart);

        //When
        Order order =  new Order(user);
        orderRepository.save(order);

        List<Product> productsFromCart = cart.getProducts();
        if (!productsFromCart.isEmpty()) {
            for (Product product : productsFromCart) {
                Long productId = product.getId();
                order.getProducts().add(productRepository.findById(productId).get());
            }
        }
        orderRepository.save(order);
        Long orderId = order.getId();
        int sizeOfOrder = orderRepository.findById(orderId).get().getProducts().size();

        //Then
        assertEquals(4, sizeOfOrder);
    }
}
