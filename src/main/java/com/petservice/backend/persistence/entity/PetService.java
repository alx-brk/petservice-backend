package com.petservice.backend.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
public class PetService implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true, length = 30)
    private String name;

    @OneToMany(mappedBy = "petService", fetch = FetchType.LAZY)
    private Set<Catalog> catalogSet;

    @ManyToMany(
            mappedBy = "petServices",
            fetch = FetchType.LAZY
    )
    private Set<Job> jobs;
}
