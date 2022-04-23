package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.exception.OrderNotFoundException;
import com.kodilla.ecommercee.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public List<Order> getOrders() {
        Iterable<Order> all = orderRepository.findAll();
        List<Order> orders = new ArrayList<>();
        all.forEach(orders::add);
        return orders;
    }

    public Order createOrder(final Order order) {
        return orderRepository.save(order);
    }

    public Order getOrderById(final Long id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.orElseThrow(() -> new OrderNotFoundException(
                "Order not found for id: " + id));
    }

    public Order updateOrder(final Order order, final Long id) {
        Optional<Order> orderEntity = orderRepository.findById(id);
        Order orderForUpdate = orderEntity.orElseThrow(() -> new OrderNotFoundException(
                "Order for update not found for id: " + id));
        orderForUpdate.setUser(order.getUser());
        return orderRepository.save(orderForUpdate);
    }

    public void deleteOrder(final Long id) {
        try {
            orderRepository.deleteById(id);
        } catch (DataAccessException e) {
            throw new OrderNotFoundException("Order not found for ID=" + id);
        }
    }
}
