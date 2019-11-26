package com.petservice.backend.persistence.repository;

import com.petservice.backend.persistence.entity.Animal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AnimalRepository extends CrudRepository<Animal, Long> {

    Animal findByNameEquals(String name);

    Set<Animal> findAllByNameIn(Set<String> names);

}
