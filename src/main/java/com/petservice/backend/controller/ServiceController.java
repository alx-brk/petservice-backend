package com.petservice.backend.controller;

import com.petservice.backend.model.ServiceDto;
import com.petservice.backend.service.ServiceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Set;

@RestController
@RequestMapping("/service")
@Api(value = "Service controller")
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @PostMapping
    @ApiOperation(value = "create")
    public ResponseEntity<HttpStatus> create(@RequestBody ServiceDto serviceDto){
        serviceService.create(serviceDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    @ApiOperation(value = "update")
    public ResponseEntity<HttpStatus> update(@RequestBody ServiceDto serviceDto){
        serviceService.update(serviceDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    @ApiOperation(value = "delete")
    public ResponseEntity<HttpStatus> delete(@RequestParam Long id){
        serviceService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    @ApiOperation(value = "get single")
    public ResponseEntity<ServiceDto> get(Long id, String name){
        return new ResponseEntity<>(serviceService.get(id, name), HttpStatus.OK);
    }

    @GetMapping("/all")
    @ApiOperation(value = "get all")
    public ResponseEntity<Set<ServiceDto>> getAll(){
        return new ResponseEntity<>(serviceService.getAll(), HttpStatus.OK);
    }
}
