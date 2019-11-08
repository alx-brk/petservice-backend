package com.petservice.backend.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class PetServiceDto {
    private Long id;
    private String name;
}
