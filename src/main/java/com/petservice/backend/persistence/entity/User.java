package com.petservice.backend.persistence.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Inheritance
@Data
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String email;

    private String phone;

    private String firstName;

    private String secondName;
}
