package com.petservice.backend.service;

import com.petservice.backend.persistence.entity.City;
import com.petservice.backend.persistence.repository.CityRepository;
import com.petservice.backend.model.CityDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public void create(CityDto cityDto){
        City city = modelMapper.map(cityDto, City.class);
        cityRepository.save(city);
    }

    @Transactional
    public void update(CityDto cityDto){
        City city = modelMapper.map(cityDto, City.class);
        cityRepository.save(city);
    }

    @Transactional
    public void delete(Long id){
        cityRepository.deleteById(id);
    }

    public CityDto get(Long id, String city){
        return modelMapper.map(cityRepository.getByIdOrCity(id, city), CityDto.class);
    }

    public Set<CityDto> getAll(){
        Set<CityDto> cityDtoSet = new HashSet<>();

        for (City city : cityRepository.findAll()){
            cityDtoSet.add(modelMapper.map(city, CityDto.class));
        }

        return cityDtoSet;
    }
}
