package com.petservice.backend.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
public class Feedback implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "petsitter", nullable = false)
    private User petsitter;

    @ManyToOne(optional = false)
    @JoinColumn(name = "author", nullable = false)
    private User author;

    @Column(nullable = false, precision = 2, scale = 1)
    private Double mark;

    private String text;

    @Column(nullable = false)
    private LocalDate creationDate;
}
