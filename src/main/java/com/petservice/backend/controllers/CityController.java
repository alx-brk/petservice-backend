package com.petservice.backend.controllers;

import com.petservice.backend.model.dto.CityDto;
import com.petservice.backend.services.CityService;
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
@RequestMapping("/city")
@Api(value = "city controller")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping("/all")
    @ApiOperation(value = "get all cities")
    public ResponseEntity<Set<CityDto>> getAll() {
        return new ResponseEntity<>(cityService.getAll(), HttpStatus.OK);
    }
}
