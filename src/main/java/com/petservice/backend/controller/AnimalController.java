package com.petservice.backend.controller;

import com.petservice.backend.model.AnimalDto;
import com.petservice.backend.service.AnimalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/animal")
@Api(value = "Animal controller")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @PostMapping
    @ApiOperation(value = "create")
    public ResponseEntity<HttpStatus> create(@RequestBody AnimalDto animalDto){
        animalService.create(animalDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    @ApiOperation(value = "update")
    public ResponseEntity<HttpStatus> update(@RequestBody AnimalDto animalDto){
        animalService.update(animalDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    @ApiOperation(value = "delete")
    public ResponseEntity<HttpStatus> delete(@RequestParam Long id){
        animalService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    @ApiOperation(value = "get single")
    public ResponseEntity<AnimalDto> get(Long id, String name){
        return new ResponseEntity<>(animalService.get(id, name), HttpStatus.OK);
    }

    @GetMapping("/all")
    @ApiOperation(value = "get all")
    public ResponseEntity<Set<AnimalDto>> getAll(){
        return new ResponseEntity<>(animalService.getAll(), HttpStatus.OK);
    }
}
