package com.petservice.backend.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Petsitter extends User{

    private String summary;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @OneToMany(
            mappedBy = "petsitter",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private Set<Image> images;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.REFRESH
    )
    @JoinTable(
            name = "petsitter_animal",
            joinColumns = {@JoinColumn(name = "petsitter_id")},
            inverseJoinColumns = {@JoinColumn(name = "animal_id")}
    )
    private Set<Animal> animals;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.REFRESH
    )
    @JoinTable(
            name = "petsitter_service",
            joinColumns = {@JoinColumn(name = "petsitter_id")},
            inverseJoinColumns = {@JoinColumn(name = "service_id")}
    )
    private Set<Service> services;
}
