package com.petservice.backend.persistence.repository;

import com.petservice.backend.persistence.entity.City;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {

    @Query("select c from City c where c.id = :id or c.city = :city")
    City getByIdOrCity(@Param("id") Long id, @Param("city") String city);
}
