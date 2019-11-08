package com.petservice.backend.services;

import com.petservice.backend.model.dto.PetServiceDto;
import com.petservice.backend.persistence.entity.PetService;
import com.petservice.backend.persistence.repository.PetServiceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class PetServiceService {

    @Autowired
    private PetServiceRepository petServiceRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Set<PetServiceDto> getAll() {
        Set<PetServiceDto> petServiceDtoSet = new HashSet<>();

        for (PetService petService : petServiceRepository.findAll()) {
            petServiceDtoSet.add(modelMapper.map(petService, PetServiceDto.class));
        }

        return petServiceDtoSet;
    }
}
