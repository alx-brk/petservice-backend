package com.petservice.backend.controllers;

import com.petservice.backend.model.dto.PetServiceDto;
import com.petservice.backend.services.PetServiceService;
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
@RequestMapping("/pet-service")
@Api(value = "pet service controller")
public class PetServiceController {

    @Autowired
    private PetServiceService petServiceService;

    @GetMapping("/all")
    @ApiOperation(value = "get all pet services")
    public ResponseEntity<Set<PetServiceDto>> getAll() {
        return new ResponseEntity<>(petServiceService.getAll(), HttpStatus.OK);
    }
}
