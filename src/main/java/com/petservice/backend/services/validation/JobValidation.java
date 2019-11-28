package com.petservice.backend.services.validation;

import com.petservice.backend.model.dto.JobDto;
import com.petservice.backend.services.exceptions.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class JobValidation implements Validation<JobDto> {

    private static final String CANNOT_BE_NULL_ERROR = "Cannot be null";
    private static final String START_DATE_ERROR = "Start date cannot be before creation date";
    private static final String END_DATE_ERROR = "End date cannot be before start date";

    @Override
    public void validateOnCreate(JobDto entity) {
        validateCommon(entity);
    }

    @Override
    public void validateOnUpdate(JobDto entity) {
        Validator.of(entity)
                .validateNotNull(
                        JobDto::getId,
                        ValidationException.builder()
                                .entity(JobDto.class)
                                .object(entity)
                                .message(CANNOT_BE_NULL_ERROR)
                                .field("id").build()
                )
                .validateNotNull(
                        JobDto::getPetsitter,
                        ValidationException.builder()
                                .entity(JobDto.class)
                                .object(entity)
                                .message(CANNOT_BE_NULL_ERROR)
                                .field("petsitter").build()
                );

        validateCommon(entity);
    }

    private void validateDates(JobDto entity) {
        if (entity.getStartDate().isBefore(entity.getCreationDate())) {
            throw ValidationException.builder()
                    .entity(JobDto.class)
                    .object(entity)
                    .message(START_DATE_ERROR)
                    .field("startDate").build();
        }

        if (entity.getEndDate().isBefore(entity.getStartDate())) {
            throw ValidationException.builder()
                    .entity(JobDto.class)
                    .object(entity)
                    .message(END_DATE_ERROR)
                    .field("endDate").build();
        }
    }

    private void validateCommon(JobDto entity) {
        Validator.of(entity)
                .validateNotNull(
                        (e) -> e,
                        ValidationException.builder()
                                .entity(JobDto.class)
                                .object(entity)
                                .message(CANNOT_BE_NULL_ERROR).build()
                )
                .validateNotNull(
                        JobDto::getCity,
                        ValidationException.builder()
                                .entity(JobDto.class)
                                .object(entity)
                                .message(CANNOT_BE_NULL_ERROR)
                                .field("city").build()
                )
                .validateNotNull(
                        JobDto::getClient,
                        ValidationException.builder()
                                .entity(JobDto.class)
                                .object(entity)
                                .message(CANNOT_BE_NULL_ERROR)
                                .field("client").build()
                )
                .validateNotNull(
                        JobDto::getJobStatus,
                        ValidationException.builder()
                                .entity(JobDto.class)
                                .object(entity)
                                .message(CANNOT_BE_NULL_ERROR)
                                .field("jobStatus").build()
                );

        validateDates(entity);
    }
}
