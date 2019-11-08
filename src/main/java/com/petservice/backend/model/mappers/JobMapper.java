package com.petservice.backend.model.mappers;

import com.petservice.backend.model.dto.JobDto;
import com.petservice.backend.persistence.entity.Job;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface JobMapper {

    Job toJob(JobDto jobDto);

    JobDto toJobDto(Job job);

    Set<Job> toJobSet(Set<JobDto> jobDtoSet);

    Set<JobDto> toJobDtoSet(Set<Job> jobSet);

    Set<JobDto> toJobDtoSet(Iterable<Job> jobIterable);
}
