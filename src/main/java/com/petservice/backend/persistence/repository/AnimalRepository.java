package com.petservice.backend.persistence.repository;

import com.petservice.backend.persistence.entity.Animal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends CrudRepository<Animal, Long> {

    Animal findByNameEquals(String name);

}
