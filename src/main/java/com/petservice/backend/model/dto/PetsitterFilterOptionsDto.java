package com.petservice.backend.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Data
@EqualsAndHashCode
public class PetsitterFilterOptionsDto {
    private CityDto city;
    private Set<AnimalDto> animals;
    private Set<PetServiceDto> services;
    private Double rating;
}
