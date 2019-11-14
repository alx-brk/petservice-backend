package com.petservice.backend.controllers;

import com.petservice.backend.persistence.entity.Image;
import com.petservice.backend.services.ImageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/image")
@Api(value = "image controller")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping(
            path = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}
    )
    @ApiOperation(value = "upload image")
    public ResponseEntity<Image> uploadFile(@PathVariable Long id, @RequestPart(name = "file") MultipartFile file) {
        return new ResponseEntity<>(imageService.storeFile(file), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "get image by id")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id){
        Image image = imageService.getFile(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(image.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getFileName() + "\"")
                .body(new ByteArrayResource(image.getPicture()));
    }
}
