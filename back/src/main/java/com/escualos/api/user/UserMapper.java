package com.escualos.api.user;

import com.escualos.domain.user.User;
import com.escualos.model.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserDTO toDto(User user);
    User toEntity(UserDTO userDTO);

}
