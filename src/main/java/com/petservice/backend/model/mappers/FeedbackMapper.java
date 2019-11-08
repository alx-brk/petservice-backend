package com.petservice.backend.model.mappers;

import com.petservice.backend.model.dto.FeedbackDto;
import com.petservice.backend.persistence.entity.Feedback;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface FeedbackMapper {

    Feedback toFeedback(FeedbackDto feedbackDto);

    FeedbackDto toFeedbackDto(Feedback feedback);

    Set<Feedback> toFeedbackSet(Set<FeedbackDto> feedbackDtoSet);

    Set<FeedbackDto> toFeedbackDtoSet(Set<Feedback> feedbackSet);

    List<Feedback> toFeedbackList(List<FeedbackDto> feedbackDtoList);

    List<FeedbackDto> toFeedbackDtoList(List<Feedback> feedbackList);

    Set<FeedbackDto> toFeedbackDtoSet(Iterable<Feedback> feedbackIterable);
}
