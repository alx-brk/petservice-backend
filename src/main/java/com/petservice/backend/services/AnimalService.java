package com.petservice.backend.services;

import com.petservice.backend.model.dto.AnimalDto;
import com.petservice.backend.model.mappers.AnimalMapper;
import com.petservice.backend.persistence.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private AnimalMapper animalMapper;

    public Set<AnimalDto> getAll() {
        return animalMapper.toAnimalDtoSet(animalRepository.findAll());
    }
}
