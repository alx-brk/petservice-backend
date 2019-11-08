package com.petservice.backend.services;

import com.petservice.backend.model.dto.FeedbackDto;
import com.petservice.backend.model.dto.UserDto;
import com.petservice.backend.persistence.entity.Feedback;
import com.petservice.backend.persistence.entity.User;
import com.petservice.backend.persistence.repository.FeedbackRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<FeedbackDto> getAllByPetsitter(UserDto petsitter) {
        List<FeedbackDto> feedbackDtoList = new ArrayList<>();

        for (Feedback feedback : feedbackRepository.findAllByPetsitterOrderByCreationDateDesc(modelMapper.map(petsitter, User.class))){
            feedbackDtoList.add(modelMapper.map(feedback, FeedbackDto.class));
        }

        return feedbackDtoList;
    }

}
