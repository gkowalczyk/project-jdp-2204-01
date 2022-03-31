package com.kodilla.ecommercee.domain;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ProductDto {
    private String name;
    private BigDecimal price;
}
