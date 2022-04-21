package com.kodilla.ecommercee.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class CartDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("user")
    private User user;

    @JsonProperty("products")
    private List<Product> products = new ArrayList<>();

    public CartDto(User user, List<Product> products) {
        this.user = user;
        this.products = products;
    }
}
