package com.petservice.backend.services;

import com.petservice.backend.model.dto.PetsitterFilterOptions;
import com.petservice.backend.model.dto.UserDto;
import com.petservice.backend.model.mappers.UserMapper;
import com.petservice.backend.persistence.entity.City;
import com.petservice.backend.persistence.entity.User;
import com.petservice.backend.persistence.enums.Role;
import com.petservice.backend.persistence.repository.UserRepository;
import com.petservice.backend.services.common.ServiceUtils;
import com.petservice.backend.services.exceptions.AuthException;
import com.petservice.backend.services.validation.CatalogValidation;
import com.petservice.backend.services.validation.UserValidation;
import com.petservice.backend.services.validation.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public UserDto getOneByIdOrEmail(Long id, String email) {
        User user = userRepository.findByIdEqualsOrEmailEquals(id, email);

        if (user == null) {
            throw AuthException.builder()
                    .entity(String.class)
                    .object(email)
                    .message(ValidationUtils.NOT_REGISTERED_ERROR)
                    .field("email")
                    .build();
        }
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
        catalogValidation.validateOnUpdate(userDto.getCatalogSet());
        userRepository.save(userMapper.toUser(userDto));
    }

    @Transactional
    public void createUser(UserDto userDto) {
        userValidation.validateOnCreate(userDto);
        catalogValidation.validateOnCreate(userDto.getCatalogSet());
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userDto.setRole(Role.USER);
        userRepository.save(userMapper.toUser(userDto));
    }
}
