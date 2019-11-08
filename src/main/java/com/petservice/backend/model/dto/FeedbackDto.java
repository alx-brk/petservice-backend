package com.petservice.backend.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class FeedbackDto {
    private Long id;
    private UserDto petsitter;
    private UserDto author;
    private Double mark;
    private String text;
}
