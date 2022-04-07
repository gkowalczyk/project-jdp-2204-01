package com.kodilla.ecommercee.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "carts")
@Data
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Long id;

    @Column (name = "QuantityY")
    private Long quantity;

    @JoinColumn
    @Column(name = "user_id")
    private Long userId;

    @JoinColumn
    @Column (name = "product_id")
    private Long productId;
}
