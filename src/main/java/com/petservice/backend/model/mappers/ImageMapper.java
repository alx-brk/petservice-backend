package com.petservice.backend.model.mappers;

import com.petservice.backend.model.dto.ImageDto;
import com.petservice.backend.persistence.entity.Image;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface ImageMapper {

    Image toImage(ImageDto ImageDto);

    ImageDto toImageDto(Image image);

    Set<Image> toImageSet(Set<ImageDto> imageDtoSet);

    Set<ImageDto> toImageDtoSet(Set<Image> imageSet);

    Set<ImageDto> toImageDtoSet(Iterable<Image> imageIterable);
}
