package com.escualos.api.user;

import com.escualos.api.UserApiDelegate;
import com.escualos.domain.user.UserRoles;
import com.escualos.domain.user.UserSrv;
import com.escualos.model.CreateUserRequest;
import com.escualos.model.UserResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
public class UserApiDelegateImpl implements UserApiDelegate {
    private static final Logger LOG = LoggerFactory.getLogger(UserApiDelegateImpl.class);
    private UserSrv userSrv;


    public UserApiDelegateImpl(UserSrv userSrv) {
        this.userSrv = userSrv;
    }

    @Override
    @PreAuthorize("hasAnyRole('" + UserRoles.SWIMMER_READ + "')")
    public Mono<ResponseEntity<Flux<UserResponse>>> getAllUsers(ServerWebExchange exchange) {
        return Mono.just(ResponseEntity.ok(userSrv.getAllUsers().map(UserMapper::toUserResponse)));
    }

    @Override
    @PreAuthorize("hasAnyRole('" + UserRoles.SWIMMER_READ + "')")
    public Mono<ResponseEntity<UserResponse>> getUserById(String id, ServerWebExchange exchange) {
        return userSrv.getUserById(id)
                .map(UserMapper::toUserResponse)
                .map(ResponseEntity::ok);
    }

    @Override
    @PreAuthorize("hasAnyRole('" + UserRoles.SWIMMER_WRITE + "')")
    public Mono<ResponseEntity<UserResponse>> postNewUser(Mono<CreateUserRequest> createUserRequest, ServerWebExchange exchange) {
        return createUserRequest
            .map(UserMapper::fromCreateUserRequest)
            .flatMap(userSrv::createUser)
            .map(user -> ResponseEntity.created(URI.create("/user/%s".formatted(user.getId()))).build());
    }
}
