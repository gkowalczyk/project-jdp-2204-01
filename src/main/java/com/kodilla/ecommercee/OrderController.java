package com.kodilla.ecommercee;

import com.kodilla.ecommercee.dto.OrderDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {

    @GetMapping
    public ResponseEntity<List<OrderDto>> getOrders() {
        List<OrderDto> ordersList = new ArrayList<>();
        ordersList.add(new OrderDto(1L,"order 1"));
        ordersList.add(new OrderDto(2L,"order 2"));
        ordersList.add(new OrderDto(3L,"order 3"));
        ordersList.add(new OrderDto(4L,"order 5"));
        return ResponseEntity.ok(ordersList);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <OrderDto> addNewOrder(@RequestBody OrderDto order) {
        OrderDto newOrder = new OrderDto(1L,"new_order");
        return ResponseEntity.ok(newOrder);
    }

    @GetMapping(value = "{orderId}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable Long orderId) {
        OrderDto order = new OrderDto( orderId,"order_test");
        return ResponseEntity.ok(order);
    }

    @PutMapping()
    public ResponseEntity<OrderDto> updateOrder(@RequestBody OrderDto order) {
        OrderDto updatedOrder = new OrderDto(order.getId(), order.getDescription());
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping(value = "{orderId}")
    public ResponseEntity <Boolean> deleteOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(Boolean.TRUE);
    }
}
