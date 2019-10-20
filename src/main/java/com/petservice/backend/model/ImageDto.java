package com.petservice.backend.model;

import lombok.Data;

import java.util.Objects;

@Data
public class ImageDto {

    private Long id;
    private String type;
    private byte[] picture;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImageDto imageDto = (ImageDto) o;
        return Objects.equals(id, imageDto.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
