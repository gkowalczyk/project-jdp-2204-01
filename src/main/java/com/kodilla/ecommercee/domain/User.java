package com.kodilla.ecommercee.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class User {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

}
