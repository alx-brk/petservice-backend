package com.petservice.backend.model.dto;

import com.petservice.backend.persistence.entity.Animal;
import com.petservice.backend.persistence.entity.City;
import com.petservice.backend.persistence.entity.PetService;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Data
@EqualsAndHashCode
public class PetsitterFilterOptions {
    private City city;
    private Set<Animal> animals;
    private Set<PetService> services;
    private Double rating;

    public boolean isEmpty() {
        if (city == null &&
                rating == null &&
                (animals == null || animals.isEmpty()) &&
                (services == null || services.isEmpty())) {
            return true;
        } else {
            return false;
        }
    }
}
