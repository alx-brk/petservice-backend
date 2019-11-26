package com.petservice.backend.persistence.repository;

import com.petservice.backend.persistence.entity.*;
import com.petservice.backend.persistence.enums.JobStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
public interface JobRepository extends CrudRepository<Job, Long> {

    List<Job> findAllByJobStatusEquals(JobStatus status);

    @Query(value = "select  job.* from job " +
            "inner join city on job.city = city.id " +
            "where job.job_status = :status " +
            "and (:city is null or city.name = :city) " +
            "and (:startDate is null or job.start_date >= :startDate) " +
            "and (:endDate is null or job.end_date <= :endDate) " +
            "and (:creationDate is null or job.creation_date >= :creationDate) " +
            "order by job.creation_date desc ",
            nativeQuery = true)
    List<Job> findByFilterOptions(@Param(value = "status") JobStatus status,
                                  @Param(value = "city") String city,
                                  @Param(value = "startDate") LocalDate startDate,
                                  @Param(value = "endDate") LocalDate endDate,
                                  @Param(value = "creationDate") LocalDate creationDate);

    @Query(value = "select  job.* from job " +
            "inner join city on job.city = city.id " +
            "inner join job_animal ja on job.id = ja.job_id " +
            "where job.job_status = :status " +
            "and (:city is null or city.name = :city) " +
            "and (:startDate is null or job.start_date >= :startDate) " +
            "and (:endDate is null or job.end_date <= :endDate) " +
            "and (:creationDate is null or job.creation_date >= :creationDate) " +
            "and ja.animal_id in (:animals) " +
            "order by job.creation_date desc ",
            nativeQuery = true)
    List<Job> findByFilterOptionsWithAnimals(@Param(value = "status") JobStatus status,
                                             @Param(value = "city") String city,
                                             @Param(value = "startDate") LocalDate startDate,
                                             @Param(value = "endDate") LocalDate endDate,
                                             @Param(value = "creationDate") LocalDate creationDate,
                                             @Param(value = "animals") String animals);

    @Query(value = "select  job.* from job " +
            "inner join city on job.city = city.id " +
            "inner join job_service js on job.id = js.job_id " +
            "where job.job_status = :status " +
            "and (:city is null or city.name = :city) " +
            "and (:startDate is null or job.start_date >= :startDate) " +
            "and (:endDate is null or job.end_date <= :endDate) " +
            "and (:creationDate is null or job.creation_date >= :creationDate) " +
            "and js.pet_service_id in (:services) " +
            "order by job.creation_date desc ",
            nativeQuery = true)
    List<Job> findByFilterOptionsWithServices(@Param(value = "status") JobStatus status,
                                              @Param(value = "city") String city,
                                              @Param(value = "startDate") LocalDate startDate,
                                              @Param(value = "endDate") LocalDate endDate,
                                              @Param(value = "creationDate") LocalDate creationDate,
                                              @Param(value = "services") String services);

    @Query(value = "select  job.* from job " +
            "inner join city on job.city = city.id " +
            "inner join job_animal ja on job.id = ja.job_id " +
            "inner join job_service js on job.id = js.job_id " +
            "where job.job_status = :status " +
            "and (:city is null or city.name = :city) " +
            "and (:startDate is null or job.start_date >= :startDate) " +
            "and (:endDate is null or job.end_date <= :endDate) " +
            "and (:creationDate is null or job.creation_date >= :creationDate) " +
            "and ja.animal_id in (:animals) " +
            "and js.pet_service_id in (:services) " +
            "order by job.creation_date desc ",
            nativeQuery = true)
    List<Job> findByFilterOptions(@Param(value = "status") JobStatus status,
                                  @Param(value = "city") String city,
                                  @Param(value = "startDate") LocalDate startDate,
                                  @Param(value = "endDate") LocalDate endDate,
                                  @Param(value = "creationDate") LocalDate creationDate,
                                  @Param(value = "animals") String animals,
                                  @Param(value = "services") String services);

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
