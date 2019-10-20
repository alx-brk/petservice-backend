package com.petservice.backend.persistence.repository;

import com.petservice.backend.persistence.entity.Client;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends UserBaseRepository<Client> {
}
