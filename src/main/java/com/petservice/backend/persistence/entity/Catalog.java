package com.petservice.backend.persistence.entity;

import com.petservice.backend.persistence.enums.Units;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Catalog implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn
    private User petsitter;

    @Id
    @ManyToOne
    @JoinColumn
    private PetService petService;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private Units units;
}
