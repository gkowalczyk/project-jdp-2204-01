package com.kodilla.ecommercee.domain;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;


@Entity
@Table(name = "GROUPS")
@Data
@NoArgsConstructor

public class Group {
    @Id
    @GeneratedValue
    @NotNull
    @Column(name="ID", unique=true)
    private Long id;

    @Column(name = "NAME")
    @NotNull
    private String name;

    @OneToMany
    @JoinColumn (name = "id")
    private Collection<Product> products = new ArrayList<Product>();

}