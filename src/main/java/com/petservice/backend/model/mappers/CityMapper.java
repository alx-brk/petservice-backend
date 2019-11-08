package com.petservice.backend.model.mappers;

import com.petservice.backend.model.dto.CityDto;
import com.petservice.backend.persistence.entity.City;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface CityMapper {

    City toCity(CityDto cityDto);

    CityDto toCityDto(City city);

    Set<City> toCitySet(Set<CityDto> cityDtoSet);

    Set<CityDto> toCityDtoSet(Set<City> citySet);

    Set<CityDto> toCityDtoSet(Iterable<City> cityIterable);
}
