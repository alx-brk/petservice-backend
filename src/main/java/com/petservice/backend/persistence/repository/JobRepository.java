package com.petservice.backend.persistence.repository;

import com.petservice.backend.persistence.entity.Job;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends CrudRepository<Job, Long> {
}
