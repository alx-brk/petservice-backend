package com.petservice.backend.services;

import com.petservice.backend.model.dto.PetsitterFilterOptions;
import com.petservice.backend.model.dto.UserDto;
import com.petservice.backend.model.mappers.UserMapper;
import com.petservice.backend.persistence.entity.User;
import com.petservice.backend.persistence.repository.UserRepository;
import com.petservice.backend.services.exceptions.NotFoundException;
import com.petservice.backend.services.validation.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserValidation userValidation;

    @Autowired
    private UserMapper userMapper;

    public UserDto getOneById(Long id) {
        return userMapper.toUserDto(
                userRepository.findById(id)
                        .orElseThrow(() -> NotFoundException.builder()
                        .entity(User.class)
                        .object(id)
                        .build())
        );
    }

    public UserDto getOneByEmail(String email) {
        return userMapper.toUserDto(
                userRepository.findByEmailEquals(email)
        );
    }

    public UserDto getOneByIdOrEmail(Long id, String email) {
        return userMapper.toUserDto(
                userRepository.findByIdEqualsOrEmailEquals(id, email)
        );
    }

    public List<UserDto> getFilteredPetsittersList(PetsitterFilterOptions petsitterFilterOptions) {
        return userMapper.toUserDtoList(
                userRepository.findByFilterOptions(
                        petsitterFilterOptions.getCity(),
                        petsitterFilterOptions.getRating(),
                        petsitterFilterOptions.getAnimals(),
                        petsitterFilterOptions.getServices()
                )
        );
    }

    @Transactional
    public void updateUser(UserDto userDto){
        userValidation.validateOnUpdate(userDto);
        userRepository.save(userMapper.toUser(userDto));
    }
}
