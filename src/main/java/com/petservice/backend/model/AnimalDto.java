package com.petservice.backend.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class AnimalDto {

    private Long id;
    private String animal;
}
