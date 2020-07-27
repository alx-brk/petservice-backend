package com.petservice.backend.config.jwt;

import lombok.Data;

@Data
public class UsernameAndPasswordAuthRequest {
    private String username;
    private String password;
}
