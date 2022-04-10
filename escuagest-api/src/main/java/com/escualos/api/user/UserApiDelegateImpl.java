package com.escualos.api.user;

import com.escualos.api.UserApiDelegate;
import com.escualos.domain.user.User;
import com.escualos.domain.user.UserRepository;
import com.escualos.model.CreateUserRequest;
import com.escualos.model.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
public class UserApiDelegateImpl implements UserApiDelegate {

    private UserRepository userRepository;

    public UserApiDelegateImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Mono<ResponseEntity<UserResponse>> getUserByName(String username, ServerWebExchange exchange) {
        return UserApiDelegate.super.getUserByName(username, exchange);
    }

    @Override
    public Mono<ResponseEntity<UserResponse>> getUserById(String id, ServerWebExchange exchange) {
        return UserApiDelegate.super.getUserById(id, exchange);
    }


    @Override
    public Mono<ResponseEntity<UserResponse>> postNewUser(Mono<CreateUserRequest> createUserRequest, ServerWebExchange exchange) {
        return createUserRequest
            .map(createUser -> User.builder()
                    .fullname(createUser.getFullname())
                    .username(createUser.getUsername())
                    .build()
            )
            .flatMap(userRepository::save)
            .map(user -> ResponseEntity.created(URI.create("/user/%s".formatted(user.getId()))).build());
    }
}
