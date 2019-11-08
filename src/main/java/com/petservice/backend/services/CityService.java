package com.petservice.backend.services;

import com.petservice.backend.model.dto.CityDto;
import com.petservice.backend.persistence.entity.City;
import com.petservice.backend.persistence.repository.CityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Set<CityDto> getAll() {
        Set<CityDto> cityDtoSet = new HashSet<>();

        for (City city : cityRepository.findAll()) {
            cityDtoSet.add(modelMapper.map(city, CityDto.class));
        }

        return cityDtoSet;
    }
}
