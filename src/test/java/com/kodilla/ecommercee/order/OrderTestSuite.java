package com.kodilla.ecommercee.order;

import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@Transactional

public class OrderTestSuite {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private GroupRepository groupRepository;

    @BeforeEach
    public void init() {
        orderRepository.deleteAll();
        userRepository.deleteAll();
        productRepository.deleteAll();
        groupRepository.deleteAll();
    }

    @Test
    void testRelationWithUser() {
        //Given
        User user = new User(null,"name", "fsdgf$#", true, new ArrayList<>(), new Cart());
        User user1 = new User(null,"name1", "fsdgf$#", true, new ArrayList<>(), new Cart());
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user);
        Order order = new Order(user);
        Order order1 = new Order(user);
        Order order2 = new Order(user1);
        userRepository.save(user);
        userRepository.save(user1);
        orderRepository.save(order);
        orderRepository.save(order1);
        orderRepository.save(order2);
        //When
        List<Order> orderList = orderRepository.findAll();
        userList.stream()
                .forEach(u -> u.setOrderList(orderList));
        user.setOrderList(orderList);
        //Then
        assertEquals(3, user.getOrderList().size());
        assertEquals(3, user1.getOrderList().size());
        //cleanup
        Iterable<Order> orderIterable = orderRepository.findAll();
        orderIterable.forEach(orders -> orderRepository.deleteById(orders.getId()));
        Iterable<User> userIterable = userRepository.findAll();
        userIterable.forEach(users -> userRepository.deleteById(users.getId()));
    }

    @Test
    void testRelationWithProduct() {

        //Given
        Order order = new Order();
        Group group = new Group();
        groupRepository.save(group);
        orderRepository.save(order);

        Product carAudi = new Product("Audi A5", "premium", new BigDecimal(1000), group);
        Product carFord = new Product("Ford Mondeo MKV", "economic", new BigDecimal(1000), group);
        Product carBmw = new Product("BMW X1", "premium", new BigDecimal(1000), group);
        productRepository.save(carAudi);
        productRepository.save(carFord);
        productRepository.save(carBmw);
        //When
        List<Product> productList = productRepository.findAll();
        order.setProducts(productList);
        orderRepository.save(order);
        //Then
        assertEquals(3, order.getProducts().size());
        assertEquals(order.getProducts().get(1).getName(), "Ford Mondeo MKV");

        //cleanup
        Iterable<Order> orderIterable = orderRepository.findAll();
        orderIterable.forEach(orders -> orderRepository.deleteById(orders.getId()));
        Iterable<Product> productIterable = productRepository.findAll();
        productIterable.forEach(p -> productRepository.deleteById(p.getId()));
        groupRepository.delete(group);
    }

    @Test
    void testGetOneOrderSuite() {
        //Given
        User user = new User(null,"name", "fsdgf$#", true, new ArrayList<>(), new Cart());
        Order order = new Order(user);
        //When
        userRepository.save(user);
        orderRepository.save(order);
        Long id = order.getId();
        Optional<Order> oneOrder = orderRepository.findById(id);
        //Then
        assertTrue(oneOrder.isPresent());
        //cleanup
        orderRepository.deleteById(id);
        userRepository.delete(user);
    }

    @Test
    void testOrderOrderRepositorySaveSuite() {

        //Given
        User user = new User(null,"name", "fsdgf$#", true, new ArrayList<>(), new Cart());
        Order order = new Order(user);
        userRepository.save(user);
        orderRepository.save(order);
        //When
        List<Order> orderList = orderRepository.findAll();
        assertEquals(1, orderList.size());
        Order order1 = new Order(user);
        orderRepository.save(order1);
        orderList = orderRepository.findAll();
        //Then
        assertEquals(2, orderList.size());
        //cleanup
        Iterable<Order> orderIterable = orderRepository.findAll();
        orderIterable.forEach(orders -> orderRepository.deleteById(orders.getId()));
        userRepository.delete(user);
    }

    @Test
    void testDeleteOrderSuite() {
        //Given
        User user = new User(null,"name", "fsdgf$#", true, new ArrayList<>(), new Cart());
        Order order = new Order(user);
        Order order1 = new Order(user);
        Order order2 = new Order(user);
        userRepository.save(user);
        orderRepository.save(order);
        orderRepository.save(order1);
        orderRepository.save(order2);
        List<Order> orderList = orderRepository.findAll();
        assertEquals(3, orderList.size());
        //When
        //orderRepository.deleteById(1L);
        orderList = orderRepository.findAll();
        //Then
        assertEquals(3, orderList.size());
        Iterable<Order> orderIterable = orderRepository.findAll();
        orderIterable.forEach(orders -> orderRepository.deleteById(orders.getId()));

        Iterable<Product> productIterable = productRepository.findAll();
        productIterable.forEach(p -> productRepository.deleteById(p.getId()));
    }

    @Test
    void testUpdateOrder() {
        //Given
        User user = new User(null,"name", "fsdgf$#", true, new ArrayList<>(), new Cart());
        User user1 = new User(null,"name1", "fsdgf$#", true, new ArrayList<>(), new Cart());
        Order order = new Order(user);
        userRepository.save(user);
        userRepository.save(user1);
        orderRepository.save(order);
        Long id = order.getId();
        //When
        Optional<Order> before = orderRepository.findById(id);
        assertTrue(before.get().getUser().getUserName().equals("name"));
        order.setUser(user1);
        orderRepository.save(order);
        Long id1 = order.getId();
        Optional<Order> after = orderRepository.findById(id1);
         //Then
        assertTrue(after.get().getUser().getUserName().equals("name1"));
        //cleanup
        Iterable<Order> orderIterable = orderRepository.findAll();
        orderIterable.forEach(orders -> orderRepository.deleteById(orders.getId()));
        Iterable<Product> productIterable = productRepository.findAll();
        productIterable.forEach(p -> productRepository.deleteById(p.getId()));
    }
}

