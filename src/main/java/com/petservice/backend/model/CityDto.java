package com.petservice.backend.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class CityDto {

    private Long id;
    private String city;
}
