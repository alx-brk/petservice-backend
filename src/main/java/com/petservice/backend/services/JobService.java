package com.petservice.backend.services;

import com.petservice.backend.model.dto.JobDto;
import com.petservice.backend.model.dto.JobFilterOptions;
import com.petservice.backend.model.mappers.JobMapper;
import com.petservice.backend.persistence.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobMapper jobMapper;

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

    @Transactional
    public void update(JobDto jobDto) {
        jobRepository.save(jobMapper.toJob(jobDto));
    }
}
