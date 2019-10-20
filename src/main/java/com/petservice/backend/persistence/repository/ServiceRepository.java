package com.petservice.backend.persistence.repository;

import com.petservice.backend.persistence.entity.Service;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends CrudRepository<Service, Long> {

    @Query("select s from Service s where s.id = :id or s.service = :service")
    Service findByIdOrService(@Param("id") Long id, @Param("service") String service);
}
