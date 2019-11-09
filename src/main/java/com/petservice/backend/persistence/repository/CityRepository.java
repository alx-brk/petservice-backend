package com.petservice.backend.persistence.repository;

import com.petservice.backend.persistence.entity.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {

    City findByNameEquals(String name);
}
