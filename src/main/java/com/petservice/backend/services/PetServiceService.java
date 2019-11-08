package com.petservice.backend.services;

import com.petservice.backend.model.dto.PetServiceDto;
import com.petservice.backend.model.mappers.PetServiceMapper;
import com.petservice.backend.persistence.repository.PetServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PetServiceService {

    @Autowired
    private PetServiceRepository petServiceRepository;

    @Autowired
    private PetServiceMapper petServiceMapper;

    public Set<PetServiceDto> getAll() {
        return petServiceMapper.toPetServiceDtoSet(petServiceRepository.findAll());
    }
}
