package com.kodilla.ecommercee.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "CARTS")
@Data
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CART_ID", nullable = false)
    private Long id;

    @Column (name = "QUANTITY")
    private Long quantity;

    @JoinColumn
    @Column(name = "USER_ID")
    private Long userId;

    @JoinColumn
    @ManyToMany
    @Column (name = "PRODUCT_ID")
    private Long productId;
}
