package com.petservice.backend.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @Column(nullable = false, length = 30)
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "avatar")
    private Image avatar;

    @Column(precision = 2, scale = 1)
    private Double rating;

    @Column(nullable = false, length = 30)
    private String phone;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    private City city;

    @Column(nullable = false)
    private Boolean activePetsitter;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_animal",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "animal_id")}
    )
    private Set<Animal> animals;

    @OneToMany(mappedBy = "petsitter", fetch = FetchType.LAZY)
    private Set<Catalog> catalogSet;

    private String description;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private Set<Feedback> clientFeedback;

    @OneToMany(mappedBy = "petsitter", fetch = FetchType.LAZY)
    private Set<Feedback> petsitterFeedback;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private Set<Job> clientJobs;

    @OneToMany(mappedBy = "petsitter", fetch = FetchType.LAZY)
    private Set<Job> petsitterJobs;
}
