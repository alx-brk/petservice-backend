package com.petservice.backend.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String animal;

    @ManyToMany(
            mappedBy = "animals",
            cascade = CascadeType.REFRESH,
            fetch = FetchType.LAZY
    )
    private Set<Petsitter> petsitters;

    @ManyToMany(
            mappedBy = "animals",
            cascade = CascadeType.REFRESH,
            fetch = FetchType.LAZY
    )
    private Set<Job> jobs;
}