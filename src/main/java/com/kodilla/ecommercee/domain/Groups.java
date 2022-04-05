package com.kodilla.ecommercee.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "groups")
public class Groups {
    @Id
    @GeneratedValue
    @NotNull
    private Long id;

    @Column(name = "name")
    @NotNull
    private String groupName;


//    @Column(name = "products")
//    private ArrayList<Products>;
}
