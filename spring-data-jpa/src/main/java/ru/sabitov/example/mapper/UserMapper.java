package ru.sabitov.example.mapper;

import org.mapstruct.Mapper;
import ru.sabitov.example.dto.UserDto;
import ru.sabitov.example.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);
}
