package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "USERS")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column(name = "username")
    private String userName;
    @Column(name = "personalkey")
    private String personalKey;
    @Column(name = "isactive")
    private boolean isActive;

    @OneToMany(
            targetEntity = Order.class,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )

    private List<Order>orderList = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER )
    @JoinColumn(name = "cart_id")
    private Cart cart;

    public User(String userName, String personalKey, boolean isActive) {
        this.userName = userName;
        this.personalKey = personalKey;
        this.isActive = isActive;
    }
}