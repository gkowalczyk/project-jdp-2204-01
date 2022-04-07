package com.kodilla.ecommercee.domain;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "groups")
@Data
@NoArgsConstructor

public class Group {
    @Id
    @GeneratedValue
    @NotNull
    @Column(name="ID", unique=true)
    private Long id;

    @Column
    @NotNull
    private String name;

    @OneToMany
    @JoinColumn (name = "product_id")
    Product product;

}
