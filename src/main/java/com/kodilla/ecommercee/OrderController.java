package com.kodilla.ecommercee;

import com.kodilla.ecommercee.dto.OrderDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("v1/orders")
public class OrderController {

    @GetMapping
    public ResponseEntity<List<OrderDto>> getOrders() {
        List<OrderDto> ordersList = new ArrayList<>();
        ordersList.add(new OrderDto());
        ordersList.add(new OrderDto());
        ordersList.add(new OrderDto());
        ordersList.add(new OrderDto());
        return ResponseEntity.ok(ordersList);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <OrderDto> addNewOrder(@RequestBody OrderDto order) {
        OrderDto newOrder = new OrderDto();
        return ResponseEntity.ok(newOrder);
    }

    @GetMapping(value = "{orderId}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable Long orderId) {
        OrderDto order = new OrderDto();
        return ResponseEntity.ok(order);
    }

    @PutMapping()
    public ResponseEntity<OrderDto> updateOrder(@RequestBody OrderDto order) {
        OrderDto updatedOrder = new OrderDto();
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping(value = "{orderId}")
    public ResponseEntity <Boolean> deleteOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(Boolean.TRUE);
    }
}
