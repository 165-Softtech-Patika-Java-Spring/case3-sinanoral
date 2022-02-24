package com.patika.mapper;

import com.patika.model.entity.User;
import com.patika.model.requestDto.UserCreateDto;
import com.patika.model.responseDto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    List<UserDto> UserListToUserDtoList(List<User> users);

    User UserCreateDtoToUser(UserCreateDto userCreateDto);

    UserDto UserToUserDto(User user);
}
