package com.petservice.backend.services;

import com.petservice.backend.model.dto.JobDto;
import com.petservice.backend.model.dto.JobFilterOptions;
import com.petservice.backend.model.mappers.JobMapper;
import com.petservice.backend.persistence.entity.City;
import com.petservice.backend.persistence.entity.Job;
import com.petservice.backend.persistence.enums.Units;
import com.petservice.backend.persistence.repository.JobRepository;
import com.petservice.backend.services.common.ServiceUtils;
import com.petservice.backend.services.validation.JobValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ServiceUtils serviceUtils;

    @Autowired
    private JobValidation jobValidation;

    @Autowired
    private JobMapper jobMapper;

    public List<JobDto> getByFilterOptions(JobFilterOptions filterOptions) {
        List<Job> jobs;

        if (filterOptions.isEmpty()) {
            jobs = jobRepository.findAllByJobStatus(filterOptions.getStatus());
        } else if (serviceUtils.hasItems(filterOptions.getAnimals()) && serviceUtils.hasItems(filterOptions.getPetServices())) {
            jobs = jobRepository.findByFilterOptions(
                    filterOptions.getStatus(),
                    Optional.ofNullable(filterOptions.getCity()).map(City::getName).orElse(null),
                    filterOptions.getStartDate(),
                    filterOptions.getEndDate(),
                    filterOptions.getCreationDate(),
                    serviceUtils.getAnimalsAsString(filterOptions.getAnimals()),
                    serviceUtils.getServicesAsString(filterOptions.getPetServices())
            );
        } else if (serviceUtils.hasItems(filterOptions.getAnimals())) {
            jobs = jobRepository.findByFilterOptionsWithAnimals(
                    filterOptions.getStatus(),
                    Optional.ofNullable(filterOptions.getCity()).map(City::getName).orElse(null),
                    filterOptions.getStartDate(),
                    filterOptions.getEndDate(),
                    filterOptions.getCreationDate(),
                    serviceUtils.getAnimalsAsString(filterOptions.getAnimals())
            );
        } else if (serviceUtils.hasItems(filterOptions.getPetServices())){
            jobs = jobRepository.findByFilterOptionsWithServices(
                    filterOptions.getStatus(),
                    Optional.ofNullable(filterOptions.getCity()).map(City::getName).orElse(null),
                    filterOptions.getStartDate(),
                    filterOptions.getEndDate(),
                    filterOptions.getCreationDate(),
                    serviceUtils.getServicesAsString(filterOptions.getPetServices())
            );
        } else {
            jobs = jobRepository.findByFilterOptions(
                    filterOptions.getStatus(),
                    Optional.ofNullable(filterOptions.getCity()).map(City::getName).orElse(null),
                    filterOptions.getStartDate(),
                    filterOptions.getEndDate(),
                    filterOptions.getCreationDate()
            );
        }

        return jobMapper.toJobDtoList(jobs);
    }

    public List<JobDto> getClientJobs(Long id) {
        return jobMapper.toJobDtoList(
                jobRepository.findAllClientJobs(id)
        );
    }

    public List<JobDto> getPetsitterJobs(Long id) {
        return jobMapper.toJobDtoList(
                jobRepository.findAllPetsitterJobs(id)
        );
    }

    @Transactional
    public void update(JobDto jobDto) {
        jobValidation.validateOnUpdate(jobDto);
        jobRepository.save(jobMapper.toJob(jobDto));
    }

    @Transactional
    public void create(JobDto jobDto) {
        jobDto.setCreationDate(LocalDate.now());
        jobValidation.validateOnCreate(jobDto);
        jobRepository.save(jobMapper.toJob(jobDto));
    }

    public List<Units> getUnits(){
        return Arrays.asList(Units.values());
    }
}
