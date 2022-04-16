package com.kodilla.ecommercee.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name="carts")
@NoArgsConstructor
public class Cart {

    @Id
    @NotNull
    @GeneratedValue
    @Column(name="CART_ID", unique=true)
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "JOIN_PRODUCT_CART",
            joinColumns = {@JoinColumn(name = "CART_ID")},
                    inverseJoinColumns = {@JoinColumn(name = "PRODUCT_ID")}
            )
    private List<Product> products = new ArrayList<>();
}
