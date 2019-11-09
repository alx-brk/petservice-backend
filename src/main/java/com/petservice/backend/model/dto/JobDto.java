package com.petservice.backend.model.dto;

import com.petservice.backend.persistence.enums.JobStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.Set;

@Data
@EqualsAndHashCode
public class JobDto {
    private Long id;
    private CityDto city;
    private UserDto client;
    private UserDto petsitter;
    private JobStatus jobStatus;
    private Set<AnimalDto> animals;
    private Set<PetServiceDto> petServices;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate creationDate;
}
