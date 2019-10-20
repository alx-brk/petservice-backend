package com.petservice.backend.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class ClientDto {

    private Long id;
    private String email;
    private String firstName;
    private String secondName;
}
