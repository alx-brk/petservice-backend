package com.petservice.backend.services;

import com.petservice.backend.model.dto.JobDto;
import com.petservice.backend.model.dto.JobFilterOptions;
import com.petservice.backend.model.mappers.JobMapper;
import com.petservice.backend.model.mappers.UserMapper;
import com.petservice.backend.persistence.enums.Units;
import com.petservice.backend.persistence.repository.JobRepository;
import com.petservice.backend.services.validation.JobValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobValidation jobValidation;

    @Autowired
    private JobMapper jobMapper;

    @Autowired
    private UserMapper userMapper;

    public List<JobDto> getByFilterOptions(JobFilterOptions jobFilterOptions) {
        return jobMapper.toJobDtoList(
                jobRepository.findByFilterOptions(
                        jobFilterOptions.getCity(),
                        jobFilterOptions.getAnimals(),
                        jobFilterOptions.getServices(),
                        jobFilterOptions.getStartDate(),
                        jobFilterOptions.getEndDate(),
                        jobFilterOptions.getCreationDate()
                )
        );
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
        jobValidation.validateOnCreate(jobDto);
        jobRepository.save(jobMapper.toJob(jobDto));
    }

    public List<Units> getUnits(){
        return Arrays.asList(Units.values());
    }
}
