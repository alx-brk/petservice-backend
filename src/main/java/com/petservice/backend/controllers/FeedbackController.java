package com.petservice.backend.controllers;

import com.petservice.backend.model.dto.FeedbackDto;
import com.petservice.backend.model.dto.UserDto;
import com.petservice.backend.services.FeedbackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/feedback")
@Api(value = "Feedback controller")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("/all")
    @ApiOperation(value = "get all feedbacks")
    public ResponseEntity<List<FeedbackDto>> getAllByPetsitter(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(feedbackService.getAllByPetsitter(userDto), HttpStatus.OK);
    }
}
