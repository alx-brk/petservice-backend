package com.petservice.backend.services;

import com.petservice.backend.model.dto.AnimalDto;
import com.petservice.backend.persistence.entity.Animal;
import com.petservice.backend.persistence.repository.AnimalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Set<AnimalDto> getAll() {
        Set<AnimalDto> animalDtoSet = new HashSet<>();

        for (Animal animal : animalRepository.findAll()) {
            animalDtoSet.add(modelMapper.map(animal, AnimalDto.class));
        }

        return animalDtoSet;
    }
}
