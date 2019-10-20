package com.petservice.backend.service;

import com.petservice.backend.persistence.entity.Service;
import com.petservice.backend.persistence.repository.ServiceRepository;
import com.petservice.backend.model.ServiceDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@org.springframework.stereotype.Service
public class ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public void create(ServiceDto serviceDto){
        Service service = modelMapper.map(serviceDto, Service.class);
        serviceRepository.save(service);
    }

    @Transactional
    public void update(ServiceDto serviceDto){
        Service service = modelMapper.map(serviceDto, Service.class);
        serviceRepository.save(service);
    }

    @Transactional
    public void delete(Long id){
        serviceRepository.deleteById(id);
    }

    public ServiceDto get(Long id, String service){
        return modelMapper.map(serviceRepository.findByIdOrService(id, service), ServiceDto.class);
    }

    public Set<ServiceDto> getAll(){
        Set<ServiceDto> serviceDtoSet = new HashSet<>();

        for (Service service : serviceRepository.findAll()){
            serviceDtoSet.add(modelMapper.map(service, ServiceDto.class));
        }

        return serviceDtoSet;
    }


}
