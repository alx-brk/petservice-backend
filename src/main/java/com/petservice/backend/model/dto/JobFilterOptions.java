package com.petservice.backend.model.dto;

import com.petservice.backend.persistence.entity.Animal;
import com.petservice.backend.persistence.entity.City;
import com.petservice.backend.persistence.entity.PetService;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.Set;

@Data
@EqualsAndHashCode
public class JobFilterOptions {
    private City city;
    private Set<Animal> animals;
    private Set<PetService> services;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate creationDate;
}
