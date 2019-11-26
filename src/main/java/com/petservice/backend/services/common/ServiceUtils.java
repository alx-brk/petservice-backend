package com.petservice.backend.services.common;

import com.petservice.backend.persistence.entity.Animal;
import com.petservice.backend.persistence.entity.PetService;
import com.petservice.backend.persistence.repository.AnimalRepository;
import com.petservice.backend.persistence.repository.PetServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ServiceUtils {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private PetServiceRepository petServiceRepository;

    public boolean hasItems(Set<?> set) {
        return set != null && !set.isEmpty();
    }

    public String getAnimalsAsString(Set<Animal> animals) {
        return animalRepository.findAllByNameIn(
                animals.stream()
                        .map(Animal::getName)
                        .collect(Collectors.toSet())
        ).stream()
                .map(Animal::getId)
                .map(String::valueOf)
                .collect(Collectors.joining(","));
    }

    public String getServicesAsString(Set<PetService> services) {
        return petServiceRepository.findAllByNameIn(
                services.stream()
                        .map(PetService::getName)
                        .collect(Collectors.toSet())
        ).stream()
                .map(PetService::getId)
                .map(String::valueOf)
                .collect(Collectors.joining(","));
    }

}
