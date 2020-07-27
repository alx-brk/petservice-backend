package com.petservice.backend.model.jwt;

import lombok.Data;

@Data
public class JwtRequest {
    private static final long serialVersionUID = 5926468583005150707L;

    private String username;

    private String password;
}
