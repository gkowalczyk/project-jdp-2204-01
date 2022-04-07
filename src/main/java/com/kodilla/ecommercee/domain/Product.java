package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

/*Temporary Product class, needed for the implementation of related DTO, Mapper, Repository controller and DB Service
within given task
Further addition of Entity features required*/

/*Final form of relations (e.g. 1:N, N:1, etc.) between Product and the rest of possible entities:
        Product, User, Cart, Order and Group of Products - to be established within task JDP220401-12*/

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "products")
@Data
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;
}
