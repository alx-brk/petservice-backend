package com.petservice.backend.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class ServiceDto {

    private Long id;
    private String service;
}
