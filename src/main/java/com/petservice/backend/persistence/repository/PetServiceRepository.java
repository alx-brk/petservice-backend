package com.petservice.backend.persistence.repository;

import com.petservice.backend.persistence.entity.PetService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PetServiceRepository extends CrudRepository<PetService, Long> {

    @Query("select ps from PetService ps")
    Set<PetService> findAll();
}
