package com.kodilla.ecommercee.domain;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "USERS")

public class User {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID", unique = true)
    private Long id;

    @Column(name = "USERNAME")
    private String userName;

    @Column(name = "PERSONALKEY")
    private String personalKey;

    // @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER) // implementation after fix the database structure
    // @JoinColumn(name = "CART_ID")
    // private Cart Cart;

    @Column(name = "ISACTIVE")
    private boolean isActive;

    // @ManyToOne(cascade = CascadeType.PERSIST)//// implementation after fix the database structure
    // @JoinColumn(name = "ORDER_ID")
    //  public Order order;
}
