package com.petservice.backend.model.mappers;

import com.petservice.backend.model.dto.AnimalDto;
import com.petservice.backend.persistence.entity.Animal;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnimalMapper {

    Animal toAnimal(AnimalDto animalDto);

    AnimalDto toAnimalDto(Animal animal);
}
