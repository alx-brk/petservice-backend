package com.petservice.backend.services;

import com.petservice.backend.config.enums.UserRole;
import com.petservice.backend.model.dto.PetsitterFilterOptions;
import com.petservice.backend.model.dto.UserDto;
import com.petservice.backend.model.mappers.UserMapper;
import com.petservice.backend.persistence.entity.City;
import com.petservice.backend.persistence.entity.User;
import com.petservice.backend.persistence.repository.UserRepository;
import com.petservice.backend.services.common.ServiceUtils;
import com.petservice.backend.services.exceptions.NotFoundException;
import com.petservice.backend.services.validation.CatalogValidation;
import com.petservice.backend.services.validation.UserValidation;
import com.petservice.backend.services.validation.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ServiceUtils serviceUtils;

    @Autowired
    private UserValidation userValidation;

    @Autowired
    private CatalogValidation catalogValidation;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDto getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> NotFoundException.builder()
                .message(ValidationUtils.USER_NOT_FOUND)
                .build());

        return userMapper.toUserDto(user);
    }

    public List<UserDto> getFilteredPetsittersList(PetsitterFilterOptions filterOptions) {
        List<User> users;

        if (filterOptions.isEmpty()) {
            users = userRepository.findAllByActivePetsitterIsTrueOrderByRatingDesc();
        } else if (serviceUtils.hasItems(filterOptions.getAnimals()) && serviceUtils.hasItems(filterOptions.getPetServices())) {
            users = userRepository.findByFilterOptions(
                    Optional.ofNullable(filterOptions.getCity()).map(City::getName).orElse(null),
                    filterOptions.getRating(),
                    serviceUtils.getAnimalsAsString(filterOptions.getAnimals()),
                    serviceUtils.getServicesAsString(filterOptions.getPetServices())
            );
        } else if (serviceUtils.hasItems(filterOptions.getAnimals())) {
            users = userRepository.findByFilterOptionsWithAnimals(
                    Optional.ofNullable(filterOptions.getCity()).map(City::getName).orElse(null),
                    filterOptions.getRating(),
                    serviceUtils.getAnimalsAsString(filterOptions.getAnimals())
            );
        } else if (serviceUtils.hasItems(filterOptions.getPetServices())){
            users = userRepository.findByFilterOptionsWithServices(
                    Optional.ofNullable(filterOptions.getCity()).map(City::getName).orElse(null),
                    filterOptions.getRating(),
                    serviceUtils.getServicesAsString(filterOptions.getPetServices())
            );
        } else {
            users = userRepository.findByFilterOptions(
                    Optional.ofNullable(filterOptions.getCity()).map(City::getName).orElse(null),
                    filterOptions.getRating()
            );
        }

        return userMapper.toUserDtoList(users);
    }

    @Transactional
    public void updateUser(UserDto userDto) {
        userValidation.validateOnUpdate(userDto);
        catalogValidation.validateOnUpdate(
                Optional.of(userDto)
                        .map(UserDto::getCatalogSet)
                        .orElse(new HashSet<>())
        );

        User newState = userMapper.toUser(userDto);

        User currentState = userRepository.findById(userDto.getId()).orElseThrow(() -> NotFoundException.builder()
                .entity(User.class)
                .object(userDto.getId())
                .build());

        newState.setPassword(currentState.getPassword());
        newState.setUserRole(currentState.getUserRole());
        newState.setAvatar(currentState.getAvatar());

        userRepository.save(newState);
    }

    @Transactional
    public UserDto createUser(UserDto userDto) {
        userValidation.validateOnCreate(userDto);
        catalogValidation.validateOnCreate(userDto.getCatalogSet());
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userDto.setUserRole(UserRole.USER_ROLE);
        User user = userRepository.save(userMapper.toUser(userDto));
        return userMapper.toUserDto(user);
    }
}
