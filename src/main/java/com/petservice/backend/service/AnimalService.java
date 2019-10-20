package com.petservice.backend.service;

import com.petservice.backend.persistence.entity.Animal;
import com.petservice.backend.persistence.repository.AnimalRepository;
import com.petservice.backend.model.AnimalDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public void create(AnimalDto animalDto){
        Animal animal = modelMapper.map(animalDto, Animal.class);
        animalRepository.save(animal);
    }

    @Transactional
    public void update(AnimalDto animalDto){
        Animal animal = modelMapper.map(animalDto, Animal.class);
        animalRepository.save(animal);
    }

    @Transactional
    public void delete(Long id){
        animalRepository.deleteById(id);
    }

    public AnimalDto get(Long id, String animal){
        return modelMapper.map(animalRepository.getByIdOrAnimal(id, animal), AnimalDto.class);
    }

    public Set<AnimalDto> getAll(){
        Set<AnimalDto> animalDtoSet = new HashSet<>();

        for (Animal animal : animalRepository.findAll()){
            animalDtoSet.add(modelMapper.map(animal, AnimalDto.class));
        }

        return animalDtoSet;
    }

}
