package com.petservice.backend.model;

import com.petservice.backend.persistence.enums.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Data
@EqualsAndHashCode
public class JobDto {

    private Long id;
    private String title;
    private String description;
    private CityDto city;
    private Set<AnimalDto> animals;
    private Set<ServiceDto> services;
    private Set<ImageDto> images;
    private ClientDto client;
    private PetsitterDto petsitter;
    private Status status;
}
