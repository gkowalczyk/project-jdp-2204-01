package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.exceptions.OrderNotFoundException;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public List<Order> getOrders(){
        return orderRepository.findAll();
    }

    public Order createOrder(final Order order){
        return orderRepository.save(order);
    }

    public Order getOrderById(final Long id){
        Optional<Order> order = orderRepository.findById(id);
        return order.orElseThrow(() -> new OrderNotFoundException(
                "Order not found for id: " + id));
    }

    public void updateOrder(final Order order){
        Optional<Order> orderEntity = orderRepository.findById(order.getId());
        Order orderForUpdate = orderEntity.orElseThrow(() -> new OrderNotFoundException(
                "Order for update not found for id: " + order.getId()));
        orderForUpdate.setCart(order.getCart());
        orderForUpdate.setUser(order.getUser());
        orderRepository.save(orderForUpdate);
    }
    public void deleteOrder(final Long id){
        orderRepository.deleteById(id);
    }







}
