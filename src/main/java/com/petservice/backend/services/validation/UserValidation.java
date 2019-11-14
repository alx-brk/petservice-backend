package com.petservice.backend.services.validation;

import com.petservice.backend.model.dto.UserDto;
import com.petservice.backend.services.exceptions.ValidationException;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class UserValidation implements Validation<UserDto> {

    private static final String CANNOT_BE_NULL_ERROR = "Cannot be null";

    // TODO: change regex, add validation of email
    private static final Pattern EMAIL_REGEX = Pattern.compile("^[\\w\\d.-]+@\\w+\\.\\w+");

    @Override
    public void validateOnCreate(UserDto entity) {

    }

    @Override
    public void validateOnUpdate(UserDto entity) {
        Validator.of(entity)
                .validateNotNull(
                        (e) -> e,
                        ValidationException.builder()
                                .entity(UserDto.class)
                                .object(entity)
                                .message(CANNOT_BE_NULL_ERROR).build()
                )
                .validateNotNull(
                        UserDto::getId,
                        ValidationException.builder()
                                .entity(UserDto.class)
                                .object(entity)
                                .message(CANNOT_BE_NULL_ERROR)
                                .field("id").build()
                )
                .validateNotNull(
                        UserDto::getEmail,
                        ValidationException.builder()
                                .entity(UserDto.class)
                                .object(entity)
                                .message(CANNOT_BE_NULL_ERROR)
                                .field("email").build()
                )
                .validateNotNull(
                        UserDto::getName,
                        ValidationException.builder()
                                .entity(UserDto.class)
                                .object(entity)
                                .message(CANNOT_BE_NULL_ERROR)
                                .field("name").build()
                )
                .validateNotNull(
                        UserDto::getPhone,
                        ValidationException.builder()
                                .entity(UserDto.class)
                                .object(entity)
                                .message(CANNOT_BE_NULL_ERROR)
                                .field("phone").build()
                )
                .validateNotNull(
                        UserDto::getCity,
                        ValidationException.builder()
                                .entity(UserDto.class)
                                .object(entity)
                                .message(CANNOT_BE_NULL_ERROR)
                                .field("city").build()
                );
    }
}
