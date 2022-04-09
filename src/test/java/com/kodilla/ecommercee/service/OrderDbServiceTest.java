package com.kodilla.ecommercee.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kodilla.ecommercee.dto.CartDto;
import com.kodilla.ecommercee.dto.OrderDto;
import com.kodilla.ecommercee.dto.UserDto;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class OrderDbServiceTest {

    @Test
    public void crudTest() throws IOException {
        ObjectMapper ob = new ObjectMapper();
        UserDto userDto = new UserDto(1L);
        CartDto cartDto = new CartDto(1L);
        OrderDto orderDto = new OrderDto(1L, cartDto, userDto);
        String s = ob.writeValueAsString(orderDto);
        System.out.println(s);

        OrderDto orderDto1 = ob.readValue(s, OrderDto.class);
        System.out.println(orderDto1.getId());

    }
}
