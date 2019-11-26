package com.petservice.backend.persistence.repository;

import com.petservice.backend.persistence.entity.Catalog;
import com.petservice.backend.persistence.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface CatalogRepository extends CrudRepository<Catalog, Long> {
    void deleteAllByPetsitter(User petsitter);
}
