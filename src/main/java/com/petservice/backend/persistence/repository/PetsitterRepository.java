package com.petservice.backend.persistence.repository;

import com.petservice.backend.persistence.entity.Petsitter;
import org.springframework.stereotype.Repository;

@Repository
public interface PetsitterRepository extends UserBaseRepository<Petsitter> {
}
