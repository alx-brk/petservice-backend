package com.petservice.backend.model.dto;

import com.petservice.backend.persistence.enums.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Data
@EqualsAndHashCode
public class UserDto {
    private Long id;
    private String email;
    private String name;
    private String password;
    private Role role;
    private ImageDto avatar;
    private Double rating;
    private String phone;
    private CityDto city;
    private Boolean activePetsitter;
    private Set<AnimalDto> animals;
    private Set<CatalogDto> catalogSet;
    private String description;
    private String token;
}
