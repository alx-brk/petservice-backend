package com.petservice.backend.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Data
@EqualsAndHashCode
public class PetsitterDto {

    private Long id;
    private String email;
    private String firstName;
    private String secondName;
    private String summary;
    private CityDto city;
    private Set<ImageDto> images;
    private Set<AnimalDto> animals;
    private Set<ServiceDto> services;
}
