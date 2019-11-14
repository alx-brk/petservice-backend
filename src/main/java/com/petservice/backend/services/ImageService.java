package com.petservice.backend.services;

import com.petservice.backend.model.dto.ImageDto;
import com.petservice.backend.persistence.entity.Image;
import com.petservice.backend.persistence.repository.ImageRepository;
import com.petservice.backend.services.exceptions.FileUploadException;
import com.petservice.backend.services.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Transactional
    public Image storeFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Image image = new Image();

        try {
            if (fileName.contains("..")) {
                throw FileUploadException.builder()
                        .entity(MultipartFile.class)
                        .object(file)
                        .message("Filename contains invalid path sequence: " + fileName)
                        .build();
            }


            image.setFileName(fileName);
            image.setType(file.getContentType());
            image.setPicture(file.getBytes());

            image = imageRepository.save(image);
            return image;
        } catch (IOException e){
            throw FileUploadException.builder()
                    .entity(Image.class)
                    .object(image)
                    .message("Can not store file")
                    .throwable(e)
                    .build();
        }
    }

    public Image getFile(Long id) {
        return imageRepository.findById(id)
                .orElseThrow(() -> NotFoundException.builder()
                .entity(Image.class)
                .object(id)
                .build());
    }
}
