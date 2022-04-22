package com.kodilla.ecommercee.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name="product_group")
public class Group {

    @Id
    @NotNull
    @GeneratedValue
    @Column(name="group_id", unique=true)
    private Long id;

    private String name;

    @OneToMany(
            targetEntity = Product.class,
            mappedBy = "group",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )

    private List<Product> products = new ArrayList<>();

    public Group(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
