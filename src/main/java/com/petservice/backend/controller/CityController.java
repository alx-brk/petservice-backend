package com.petservice.backend.controller;

import com.petservice.backend.model.CityDto;
import com.petservice.backend.service.CityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Set;

@RestController
@RequestMapping("/city")
@Api(value = "City controller")
public class CityController {

    @Autowired
    private CityService cityService;

    @PostMapping
    @ApiOperation(value = "create")
    public ResponseEntity<HttpStatus> create(@RequestBody CityDto cityDto){
        cityService.create(cityDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    @ApiOperation(value = "update")
    public ResponseEntity<HttpStatus> update(@RequestBody CityDto cityDto){
        cityService.update(cityDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    @ApiOperation(value = "delete")
    public ResponseEntity<HttpStatus> delete(@RequestParam Long id){
        cityService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    @ApiOperation(value = "get single")
    public ResponseEntity<CityDto> get(Long id, String name){
        return new ResponseEntity<>(cityService.get(id, name), HttpStatus.OK);
    }

    @GetMapping("/all")
    @ApiOperation(value = "get all")
    public ResponseEntity<Set<CityDto>> getAll(){
        return new ResponseEntity<>(cityService.getAll(), HttpStatus.OK);
    }
}
