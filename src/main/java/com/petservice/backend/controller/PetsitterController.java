package com.petservice.backend.controller;

import com.petservice.backend.model.PetsitterDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Set;

@RestController
@RequestMapping("/petsitter")
@Api(value = "Petsiiter controller")
public class PetsitterController {

    @PostMapping
    @ApiOperation(value = "create")
    public ResponseEntity<HttpStatus> create(@RequestBody PetsitterDto petsitterDto){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    @ApiOperation(value = "update")
    public ResponseEntity<HttpStatus> update(@RequestBody PetsitterDto petsitterDto){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    @ApiOperation(value = "delete")
    public ResponseEntity<HttpStatus> delete(@RequestParam Long id){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    @ApiOperation(value = "get single")
    public ResponseEntity<PetsitterDto> get(Long id, String email){
        return new ResponseEntity<>(new PetsitterDto(), HttpStatus.OK);
    }

    @GetMapping("/all")
    @ApiOperation(value = "get all")
    public ResponseEntity<Set<PetsitterDto>> getAll(){
        return new ResponseEntity<>(Collections.emptySet(), HttpStatus.OK);
    }
}
