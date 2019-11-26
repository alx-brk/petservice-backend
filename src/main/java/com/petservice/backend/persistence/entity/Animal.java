package com.petservice.backend.persistence.entity;

import com.google.common.base.Objects;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
public class Animal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Animal)) return false;
        Animal animal = (Animal) o;
        return Objects.equal(id, animal.id) &&
                Objects.equal(name, animal.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, name);
    }
}
