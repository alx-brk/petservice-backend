package com.petservice.backend.persistence.entity;

import com.petservice.backend.persistence.enums.JobStatus;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
public class Job implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "client", nullable = false)
    private User client;

    @ManyToOne
    @JoinColumn(name = "petsitter")
    private User petsitter;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private JobStatus jobStatus;

    @ManyToMany
    @JoinTable(
            name = "job_animal",
            joinColumns = {@JoinColumn(name = "job_id")},
            inverseJoinColumns = {@JoinColumn(name = "animal_id")}
    )
    private Set<Animal> animals;

    @ManyToMany
    @JoinTable(
            name = "job_service",
            joinColumns = {@JoinColumn(name = "job_id")},
            inverseJoinColumns = {@JoinColumn(name = "pet_service_id")}
    )
    private Set<PetService> petServices;

    private String description;

    @Column
    private LocalDate startDate;

    @Column
    private LocalDate endDate;

    @Column(nullable = false)
    private LocalDate creationDate;
}
