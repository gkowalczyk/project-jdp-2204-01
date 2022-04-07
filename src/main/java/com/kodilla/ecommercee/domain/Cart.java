package com.kodilla.ecommercee.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "carts")
@Data
@NoArgsConstructor
public class Cart {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

}
