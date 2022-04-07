package com.kodilla.ecommercee.domain;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
@Entity
@Table (name = "GROUP")
@Data
public class Group {
    @Id
    @GeneratedValue
    @NotNull
    @Column(name="ID", unique=true)
    private Long id;

    @Column(name = "NAME")
    @NotNull
    private String groupName;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    Product product;
}
