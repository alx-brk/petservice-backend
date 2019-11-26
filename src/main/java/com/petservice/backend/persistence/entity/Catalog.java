package com.petservice.backend.persistence.entity;

import com.google.common.base.Objects;
import com.petservice.backend.persistence.enums.Units;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Catalog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "petsitter_id")
    private User petsitter;

    @ManyToOne
    @JoinColumn(name = "pet_servive_id")
    private PetService petService;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private Units units;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Catalog)) return false;
        Catalog catalog = (Catalog) o;
        return Objects.equal(id, catalog.id) &&
                Objects.equal(petsitter, catalog.petsitter) &&
                Objects.equal(petService, catalog.petService) &&
                Objects.equal(price, catalog.price) &&
                units == catalog.units;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, petsitter, petService, price, units);
    }
}
