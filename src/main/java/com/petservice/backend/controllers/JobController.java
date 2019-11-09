package com.petservice.backend.controllers;

import com.petservice.backend.model.dto.JobDto;
import com.petservice.backend.model.dto.JobFilterOptions;
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

    @GetMapping("/search")
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
}
