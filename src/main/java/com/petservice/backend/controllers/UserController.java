package com.petservice.backend.controllers;

import com.petservice.backend.config.jwt.JwtTokenUtils;
import com.petservice.backend.model.dto.PetsitterFilterOptions;
import com.petservice.backend.model.dto.UserDto;
import com.petservice.backend.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Api(value = "user controller")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @PostMapping("/search")
    @ApiOperation(value = "get filtered list of petsitters")
    public ResponseEntity<List<UserDto>> getByFilterOptions(@RequestBody PetsitterFilterOptions petsitterFilterOptions) {
        return new ResponseEntity<>(userService.getFilteredPetsittersList(petsitterFilterOptions), HttpStatus.OK);
    }

    @PutMapping
    @ApiOperation(value = "update user")
    public ResponseEntity<HttpStatus> updateUser(@RequestBody UserDto userDto) {
        jwtTokenUtils.validateAccess(userDto.getId());
        userService.updateUser(userDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "create user")
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.OK);
    }

    @GetMapping
    @ApiOperation(value = "get user profile information")
    public ResponseEntity<UserDto> getProfile(@RequestParam Long id){
        jwtTokenUtils.validateAccess(id);
        return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
    }
}
