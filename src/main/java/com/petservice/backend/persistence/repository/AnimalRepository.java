package com.petservice.backend.persistence.repository;

import com.petservice.backend.persistence.entity.Animal;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends CrudRepository<Animal, Long> {

    @Modifying
    void deleteById(Long id);

    @Query("select a from Animal a where a.id = :id or a.animal = :animal")
    Animal getByIdOrAnimal(@Param("id") Long id, @Param("animal") String animal);
}
