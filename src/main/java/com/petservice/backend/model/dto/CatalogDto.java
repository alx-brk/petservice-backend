package com.petservice.backend.model.dto;

import com.petservice.backend.persistence.enums.Units;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class CatalogDto {
    private UserDto petsitter;
    private PetServiceDto petService;
    private Integer price;
    private Units units;
}
