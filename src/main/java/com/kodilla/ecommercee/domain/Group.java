package com.kodilla.ecommercee.domain;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Data
@NoArgsConstructor
public class Group {
    @Id
    @GeneratedValue
    @NotNull
    @Column(name="id", unique=true)
    private Long id;

    @Column
    @NotNull
    private String groupName;

    @ManyToOne
    @JoinColumn (name = "product_id")
    Product product;
}
