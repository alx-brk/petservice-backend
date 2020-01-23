package com.petservice.backend.controllers;

import com.petservice.backend.config.jwt.JwtTokenProvider;
import com.petservice.backend.model.dto.PetsitterFilterOptions;
import com.petservice.backend.model.dto.UserDto;
import com.petservice.backend.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/user")
@Api(value = "user controller")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/search")
    @ApiOperation(value = "get filtered list of petsitters")
    public ResponseEntity<List<UserDto>> getByFilterOptions(@RequestBody PetsitterFilterOptions petsitterFilterOptions) {
        return new ResponseEntity<>(userService.getFilteredPetsittersList(petsitterFilterOptions), HttpStatus.OK);
    }

    @PutMapping
    @ApiOperation(value = "update user")
    public ResponseEntity<HttpStatus> updateUser(@RequestBody UserDto userDto) {
        userService.updateUser(userDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "create user")
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.OK);
    }

    @GetMapping("/login")
    @ApiOperation(value = "login")
    public ResponseEntity<?> login(Principal principal) {
        if (principal == null) {
            return ResponseEntity.ok(principal);
        }
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) principal;
        UserDto userDto = userService.getOneByIdOrEmail(null, authenticationToken.getName());
        userDto.setToken(jwtTokenProvider.generateToken(authenticationToken));
        return new ResponseEntity<>(userDto, HttpStatus.OK);


    }
}
