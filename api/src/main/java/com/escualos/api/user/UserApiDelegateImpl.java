package com.escualos.api.user;

import com.escualos.api.UserApiDelegate;
import com.escualos.domain.user.UserSrv;
import com.escualos.model.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class UserApiDelegateImpl implements UserApiDelegate {
    private static final Logger LOG = LoggerFactory.getLogger(UserApiDelegateImpl.class);
    private UserSrv userSrv;


    public UserApiDelegateImpl(UserSrv userSrv) {
        this.userSrv = userSrv;
    }

    @Override
    public Mono<ResponseEntity<UserDTO>> getLoggedUserInfo(ServerWebExchange exchange) {
        return userSrv.getUserInfo(exchange.getPrincipal())
                .map(user -> UserMapper.toUserDTO(user))
                .map(ResponseEntity::ok);
    }

}
