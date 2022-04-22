package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.ProductRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class ProductTestSuit {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private OrderRepository orderRepository;

    private Group jackets;
    private Group blouses;

    @BeforeEach
    public void beforeEach() {
        jackets = new Group("jackets");
        blouses = new Group("blouses");
        jackets = groupRepository.save(jackets);
        blouses = groupRepository.save(blouses);
        Product product1 = new Product("Denim jacket", "Jacket in sturdy cotton denim with a collar", new BigDecimal(250.99), jackets);
        Product product2 = new Product("Oversized biker jacket", "Oversized biker jacket with a diagonal zip down ", new BigDecimal(350.99), jackets);
        Product product3 = new Product("V-neck blouse", "V-neck blouse in softly draping woven fabric", new BigDecimal(60.99), blouses);
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
    }

   @AfterEach
    public void cleanUp() {
        List<Product> allProducts = productRepository.findAll();
        allProducts.forEach(product -> productRepository.deleteById(product.getId()));
        groupRepository.delete(jackets);
        groupRepository.delete(blouses);
    }


    @Test
    public void testCreateProduct() {
        //Given
        List<Product> allProducts = productRepository.findAll();
        assertEquals(3, allProducts.size());
        Product product = new Product("Baseball jacket", "Baseball jacket in woven fabric", new BigDecimal(150.99), jackets);
        //When
        product = productRepository.save(product);
        //Then
        assertNotNull(product.getId());
        allProducts = productRepository.findAll();
        assertEquals(4, allProducts.size());
    }

    @Test
    public void testReadProduct() {
        //When
        List<Product> allProducts = productRepository.findAll();
        Product product = productRepository.findById(1L).get();
        //Then
        assertNotNull(product);
        assertEquals(1L, (long) product.getId());
        assertEquals(jackets, product.getGroup());
    }

    @Test
    public void testUpdateProduct() {
        //Given
        String newName = "Dummy Jacket";
        Product product = productRepository.findById(2L).get();
        assertNotNull(product);
        //When
        product.setName(newName);
        product.setGroup(blouses);
        productRepository.save(product);
        product = productRepository.findById(2L).get();
        //Then
        assertEquals(newName, product.getName());
        assertEquals(blouses, product.getGroup());
    }

    @Test
    public void testDeleteProduct() {
        //Given
        List<Product> allProducts = productRepository.findAll();
        assertEquals(3, allProducts.size());
        //When
        productRepository.deleteById(3L);
        allProducts = productRepository.findAll();
        //Then
        assertEquals(2, allProducts.size());
        Optional<Product> product = productRepository.findById(3L);
        assertFalse(product.isPresent());
    }

    @Test
    public void testGroupStaysWhenProductDeleted() {
        //When
        productRepository.deleteById(3L);
        List<Group> groups = groupRepository.findAll();

        //Then
        assertFalse(groups.isEmpty());
        assertEquals(2, groups.size());

    }

    @Test
    public void testProductStaysWhenRemovedFromCart() {
        //Given
        Cart cart = new Cart();
        List<Product> allProducts = productRepository.findAll();
        cart.setProducts(allProducts);
        cartRepository.save(cart);
        assertNotNull(cart.getCartId());
        assertEquals(3, cart.getProducts().size());
        //When
        cart.getProducts().remove(1);
        cart = cartRepository.save(cart);
        //Then
        assertEquals(2, cart.getProducts().size());
        allProducts = productRepository.findAll();
        assertEquals(3, allProducts.size());
    }

    @Test
    public void testProductStaysWhenRemovedFromOrder() {
        //Given
        Order order= new Order();
        List<Product> allProducts = productRepository.findAll();
        order.setProducts(allProducts);
        orderRepository.save(order);
        assertNotNull(order.getId());
        assertEquals(3, order.getProducts().size());
        //When
        order.getProducts().remove(1);
        order = orderRepository.save(order);
        //Then
        assertEquals(2, order.getProducts().size());
        allProducts = productRepository.findAll();
        assertEquals(3, allProducts.size());
    }
}

