package com.petservice.backend.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class ImageDto {
    private Long id;
    private String type;
    private byte[] picture;
}
