package com.petservice.backend.model.mappers;

import com.petservice.backend.model.dto.CatalogDto;
import com.petservice.backend.model.dto.UserDto;
import com.petservice.backend.persistence.entity.Catalog;
import com.petservice.backend.persistence.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserDto userDto);

    @Mappings({
            @Mapping(target = "avatar.picture", ignore = true),
            @Mapping(target = "password", ignore = true)
    })
    UserDto toUserDto(User user);

    Set<User> toUserSet(Set<UserDto> userDtoSet);

    Set<UserDto> toUserDtoSet(Set<User> userSet);

    List<User> toUserList(List<UserDto> userDtoList);

    List<UserDto> toUserDtoList(List<User> userList);

    Set<UserDto> toUserDtoSet(Iterable<User> userIterable);

    @Mapping(target = "petsitter", ignore = true)
    CatalogDto catalogToCatalogDto(Catalog catalog);
}
