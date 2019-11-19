package com.petservice.backend.persistence.repository;

import com.petservice.backend.persistence.entity.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
public interface JobRepository extends CrudRepository<Job, Long> {

    @Query("select job from Job job " +
            "where job.city = :city " +
            "and job.animals in :animals " +
            "and job.petServices in :services " +
            "and job.startDate >= :start " +
            "and job.endDate <= :end " +
            "and job.creationDate >= :create " +
            "order by job.creationDate desc ")
    List<Job> findByFilterOptions(@Param(value = "city")City city,
                                  @Param(value = "animals")Set<Animal> animals,
                                  @Param(value = "services")Set<PetService> services,
                                  @Param(value = "start")LocalDate startDate,
                                  @Param(value = "end")LocalDate endDate,
                                  @Param(value = "create")LocalDate creationDate);

    @Query("select job from Job job " +
            "inner join job.client client " +
            "where client.id = :id " +
            "order by job.jobStatus desc, job.creationDate desc ")
    List<Job> findAllClientJobs(@Param(value = "id") Long id);

    @Query("select job from Job job " +
            "inner join job.petsitter petsitter " +
            "where petsitter.id = :id " +
            "order by job.creationDate desc ")
    List<Job> findAllPetsitterJobs(@Param(value = "id") Long id);
}
