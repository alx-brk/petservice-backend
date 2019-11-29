package com.petservice.backend.services.validation;

import com.petservice.backend.model.dto.UserDto;
import com.petservice.backend.services.exceptions.ValidationException;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class UserValidation implements Validation<UserDto> {

    private static final String CANNOT_BE_NULL_ERROR = "Cannot be null";
    private static final String NOT_EMAIL_ERROR = "Not a valid email";

    private static final Pattern EMAIL_REGEX = Pattern.compile("^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");

    @Override
    public void validateOnCreate(UserDto entity) {
        validateCommon(entity);
    }

    @Override
    public void validateOnUpdate(UserDto entity) {
        Validator.of(entity)
                .validateNotNull(
                        UserDto::getId,
                        ValidationException.builder()
                                .entity(UserDto.class)
                                .object(entity)
                                .message(CANNOT_BE_NULL_ERROR)
                                .field("id").build()
                );
    }

    private void validateCommon(UserDto entity) {
        Validator.of(entity)
                .validateNotNull(
                        (e) -> e,
                        ValidationException.builder()
                                .entity(UserDto.class)
                                .object(entity)
                                .message(CANNOT_BE_NULL_ERROR).build()
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
                ).validateCondition(
                        UserDto::getEmail,
                        e -> EMAIL_REGEX.matcher(e).matches(),
                        ValidationException.builder()
                                .entity(UserDto.class)
                                .object(entity)
                                .message(NOT_EMAIL_ERROR)
                                .field("email").build()
                );
    }
}
