package com.petservice.backend.model.mappers;

import com.petservice.backend.model.dto.UserDto;
import com.petservice.backend.persistence.entity.User;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserDto userDto);

    UserDto toUserDto(User user);

    Set<User> toUserSet(Set<UserDto> userDtoSet);

    Set<UserDto> toUserDtoSet(Set<User> userSet);

    List<User> toUserList(List<UserDto> userDtoList);

    List<UserDto> toUserDtoList(List<User> userList);

    Set<UserDto> toUserDtoSet(Iterable<User> userIterable);
}
