package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.dto.OrderDto;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderDto>> getOrders() {
        List<Order> orders = orderService.getOrders();
        List<OrderDto> ordersDto = OrderMapper.mapToOrderDtoList(orders);
        return ResponseEntity.ok(ordersDto);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDto> addNewOrder(@RequestBody OrderDto orderDto) {
        Order order = orderService.createOrder(OrderMapper.mapToOrder(orderDto));
        return ResponseEntity.ok(OrderMapper.mapToOrderDto(order));
    }

    @GetMapping(value = "{orderId}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable Long orderId) {
        Order order = orderService.getOrderById(orderId);
        return ResponseEntity.ok(OrderMapper.mapToOrderDto(order));
    }

    @PutMapping(value = "{orderId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDto> updateOrder(@PathVariable Long orderId, @RequestBody OrderDto orderDto) {
        Order order = OrderMapper.mapToOrder(orderDto);
        Order updatedOrder = orderService.updateOrder(order, orderId);
        return ResponseEntity.ok(OrderMapper.mapToOrderDto(updatedOrder));
    }

    @DeleteMapping(value = "{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.ok().build();
    }
}
