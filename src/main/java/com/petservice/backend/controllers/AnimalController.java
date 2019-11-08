package com.petservice.backend.controllers;

import com.petservice.backend.model.dto.AnimalDto;
import com.petservice.backend.services.AnimalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/animal")
@Api(value = "Animal controller")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @GetMapping("/all")
    @ApiOperation(value = "get all animals")
    public ResponseEntity<Set<AnimalDto>> getAll() {
        return new ResponseEntity<>(animalService.getAll(), HttpStatus.OK);
    }
}
