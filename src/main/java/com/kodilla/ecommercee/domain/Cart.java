package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@Table(name="carts")
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CART_ID", unique=true, nullable = false)
    private Long cartId;

    @OneToOne(mappedBy = "cart")
    private User User;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "JOIN_PRODUCT_CART",
            joinColumns = {@JoinColumn(name = "CART_ID")},
                    inverseJoinColumns = {@JoinColumn(name = "PRODUCT_ID")}
    )
    private List<Product> products = new ArrayList<>();

}