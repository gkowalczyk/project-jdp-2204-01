package com.kodilla.ecommercee.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {

    private Long id;
    @JsonIgnore
    private User user;
    private List<Product> products = new ArrayList<>();

    public CartDto(User user, List<Product> products) {
        this.user = user;
        this.products = products;
    }
}
