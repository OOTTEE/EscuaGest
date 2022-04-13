package com.escualos.api.user;

import com.escualos.domain.user.User;
import com.escualos.model.UserResponse;

public class UserMapper {

    public static UserResponse toUserResponse(User user){
        return new UserResponse()
                .username(user.getUsername())
                .fullname(user.getFullname())
                .id(user.getId());
    }

}
