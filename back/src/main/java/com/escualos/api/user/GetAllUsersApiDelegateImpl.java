package com.escualos.api.user;

import com.escualos.api.controller.GetLoggedUserApiDelegate;
import com.escualos.api.model.UserDTO;
import com.escualos.core.domain.user.UserSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class GetLoggedUserApiDelegateImpl implements GetLoggedUserApiDelegate {
    @Autowired
    private UserSrv userSrv;

    @Override
    public Mono<ResponseEntity<UserDTO>> getLoggedUser(ServerWebExchange exchange) {
        return userSrv.getUserInfo(exchange.getPrincipal())
                .map(UserMapper.INSTANCE::toDto)
                .map(ResponseEntity::ok);
    }

}
