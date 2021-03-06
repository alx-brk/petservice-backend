package com.petservice.backend.controllers;

import com.petservice.backend.config.jwt.JwtTokenUtils;
import com.petservice.backend.model.dto.JobDto;
import com.petservice.backend.model.dto.JobFilterOptions;
import com.petservice.backend.persistence.enums.JobStatus;
import com.petservice.backend.persistence.enums.Units;
import com.petservice.backend.services.JobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/job")
@Api(value = "Job controller")
public class JobController {

    @Autowired
    private JobService jobService;

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @PostMapping(value = "/search")
    @ApiOperation(value = "get jobs by filter options")
    public ResponseEntity<List<JobDto>> getByFilterOptions(@RequestBody JobFilterOptions jobFilterOptions) {
        return new ResponseEntity<>(jobService.getByFilterOptions(jobFilterOptions), HttpStatus.OK);
    }

    @PutMapping
    @ApiOperation(value = "update job")
    public ResponseEntity<HttpStatus> update(@RequestBody JobDto jobDto) {
        jobService.update(jobDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "create job")
    public ResponseEntity<HttpStatus> create(@RequestBody JobDto jobDto) {
        jobService.create(jobDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/units/all")
    @ApiOperation(value = "get all units")
    public ResponseEntity<List<Units>> getUnits() {
        return new ResponseEntity<>(jobService.getUnits(), HttpStatus.OK);
    }

    @GetMapping("/statuses/all")
    @ApiOperation(value = "get all job statuses")
    public ResponseEntity<List<JobStatus>> getStatuses(){
        return new ResponseEntity<>(jobService.getStatuses(), HttpStatus.OK);
    }

    @GetMapping("/client-orders")
    @ApiOperation(value = "get all client orders")
    public ResponseEntity<List<JobDto>> getClientJobs(@RequestParam(value = "id") Long id) {
        jwtTokenUtils.validateAccess(id);
        return new ResponseEntity<>(jobService.getClientJobs(id), HttpStatus.OK);
    }

    @GetMapping("/petsitter-orders")
    @ApiOperation(value = "get all petsitter orders")
    public ResponseEntity<List<JobDto>> getPetsitterJobs(@RequestParam(value = "id") Long id) {
        jwtTokenUtils.validateAccess(id);
        return new ResponseEntity<>(jobService.getPetsitterJobs(id), HttpStatus.OK);
    }
}
