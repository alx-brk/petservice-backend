package com.petservice.backend.services.validation;

import com.petservice.backend.model.dto.CatalogDto;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class CatalogValidation implements Validation<CatalogDto> {

    @Override
    public void validateOnCreate(CatalogDto entity) {
        //TODO: implement
    }

    @Override
    public void validateOnUpdate(CatalogDto entity) {
        //TODO: implement
    }

    public void validateOnUpdate(Set<CatalogDto> set) {
        for (CatalogDto item : set) {
            validateOnUpdate(item);
        }
    }

    public void validateOnCreate(Set<CatalogDto> set) {
        for (CatalogDto item : set) {
            validateOnCreate(item);
        }
    }
}
