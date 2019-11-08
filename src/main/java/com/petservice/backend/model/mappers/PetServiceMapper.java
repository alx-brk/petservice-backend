package com.petservice.backend.model.mappers;

import com.petservice.backend.model.dto.PetServiceDto;
import com.petservice.backend.persistence.entity.PetService;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface PetServiceMapper {

    PetService toPetService(PetServiceDto petServiceDto);

    PetServiceDto toPetServiceDto(PetService petService);

    Set<PetService> toPetServiceSet(Set<PetServiceDto> petServiceDtoSet);

    Set<PetServiceDto> toPetServiceDtoSet(Set<PetService> petServiceSet);

    Set<PetServiceDto> toPetServiceDtoSet(Iterable<PetService> petServiceIterable);
}
