package com.petservice.backend.persistence.entity;

import com.petservice.backend.persistence.enums.Status;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.REFRESH
    )
    @JoinTable(
            name = "job_animal",
            joinColumns = {@JoinColumn(name = "job_id")},
            inverseJoinColumns = {@JoinColumn(name = "animal_id")}
    )
    private Set<Animal> animals;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.REFRESH
    )
    @JoinTable(
            name = "job_service",
            joinColumns = {@JoinColumn(name = "job_id")},
            inverseJoinColumns = {@JoinColumn(name = "service_id")}
    )
    private Set<Service> services;

    @OneToMany(
            mappedBy = "job",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private Set<Image> images;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "petsitter_id")
    private Petsitter petsitter;

    @Column(nullable = false)
    private Status status;

}
