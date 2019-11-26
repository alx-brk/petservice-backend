package com.petservice.backend.model.mappers;

import com.petservice.backend.model.dto.JobDto;
import com.petservice.backend.model.dto.UserDto;
import com.petservice.backend.persistence.entity.Job;
import com.petservice.backend.persistence.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface JobMapper {

    Job toJob(JobDto jobDto);

    JobDto toJobDto(Job job);

    Set<Job> toJobSet(Set<JobDto> jobDtoSet);

    Set<JobDto> toJobDtoSet(Set<Job> jobSet);

    List<Job> toJobList(List<JobDto> jobDtoList);

    List<JobDto> toJobDtoList(List<Job> jobList);

    Set<JobDto> toJobDtoSet(Iterable<Job> jobIterable);

    @Mapping(target = "catalogSet", ignore = true)
    UserDto toUserDto(User user);
}
