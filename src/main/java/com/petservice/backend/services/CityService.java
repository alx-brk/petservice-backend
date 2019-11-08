package com.petservice.backend.services;

import com.petservice.backend.model.dto.CityDto;
import com.petservice.backend.model.mappers.CityMapper;
import com.petservice.backend.persistence.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CityMapper cityMapper;

    public Set<CityDto> getAll() {
        return cityMapper.toCityDtoSet(cityRepository.findAll());
    }
}
