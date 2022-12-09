package com.escualos.api.user;

import com.escualos.api.controller.GetLoggedUserApi;
import com.escualos.api.model.UserDTO;
import com.escualos.api.user.mappers.UserMapper;
import com.escualos.core.user.UserSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class GetLoggedUserApiDelegateImpl implements GetLoggedUserApi {
    @Autowired
    private UserSrv userSrv;

    @Override
    public Mono<ResponseEntity<UserDTO>> getLoggedUser(ServerWebExchange exchange) {
        return userSrv.getUserInfo(exchange.getPrincipal())
                .map(UserMapper.INSTANCE::toDto)
                .map(ResponseEntity::ok);
    }

}
