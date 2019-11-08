package com.petservice.backend.services;

import com.petservice.backend.model.dto.FeedbackDto;
import com.petservice.backend.model.dto.UserDto;
import com.petservice.backend.model.mappers.FeedbackMapper;
import com.petservice.backend.model.mappers.UserMapper;
import com.petservice.backend.persistence.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private FeedbackMapper feedbackMapper;

    @Autowired
    private UserMapper userMapper;

    public List<FeedbackDto> getAllByPetsitter(UserDto petsitter) {
        return feedbackMapper.toFeedbackDtoList(
                feedbackRepository.findAllByPetsitterOrderByCreationDateDesc(
                        userMapper.toUser(petsitter)
                )
        );
    }

}
