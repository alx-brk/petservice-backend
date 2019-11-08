package com.petservice.backend.model.mappers;

import com.petservice.backend.model.dto.AnimalDto;
import com.petservice.backend.persistence.entity.Animal;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface AnimalMapper {

    Animal toAnimal(AnimalDto animalDto);

    AnimalDto toAnimalDto(Animal animal);

    Set<Animal> toAnimalSet(Set<AnimalDto> animalDtoSet);

    Set<AnimalDto> toAnimalDtoSet(Set<Animal> animalSet);

    Set<AnimalDto> toAnimalDtoSet(Iterable<Animal> animalIterable);
}
