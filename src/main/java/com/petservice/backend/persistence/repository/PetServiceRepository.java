package com.petservice.backend.persistence.repository;

import com.petservice.backend.persistence.entity.PetService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetServiceRepository extends CrudRepository<PetService, Long> {
}
