package com.escualos.api.user;

import com.escualos.domain.user.User;
import com.escualos.model.UserDTO;

import java.util.Set;

public class UserMapper {

    public static UserDTO toUserDTO(User user){
        final Set<String> groups = user.getGroups();
        return new UserDTO()
                .id(user.getId())
                .username(user.getUsername())
                .fullname(user.getFullname())
                .email(user.getEmail())
                .emailVerified(user.isEmailVerified())
                .firstName(user.getFirstName())
                .lastsNames(user.getLastsNames())
                .groups(user.getGroups());
    }

}
