package com.petservice.backend.model.mappers;

import com.petservice.backend.model.dto.CatalogDto;
import com.petservice.backend.persistence.entity.Catalog;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface CatalogMapper {

    Catalog toCatalog(CatalogDto catalogDto);

    CatalogDto toCatalogDto(Catalog catalog);

    Set<Catalog> toCatalogSet(Set<CatalogDto> catalogDtoSet);

    Set<CatalogDto> toCatalogDtoSet(Set<Catalog> catalogSet);

    Set<CatalogDto> toCatalogDtoSet(Iterable<Catalog> catalogIterable);
}
