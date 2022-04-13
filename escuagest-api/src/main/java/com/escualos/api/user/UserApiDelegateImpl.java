package com.escualos.api.user;

import com.escualos.api.UserApiDelegate;
import com.escualos.api.testController;
import com.escualos.domain.user.User;
import com.escualos.domain.user.UserRepository;
import com.escualos.domain.user.UserRoles;
import com.escualos.model.CreateUserRequest;
import com.escualos.model.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
public class UserApiDelegateImpl implements UserApiDelegate {

    private UserRepository userRepository;

    public UserApiDelegateImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @PreAuthorize("hasAnyRole('" + UserRoles.SWIMMER_READ + "')")
    public Mono<ResponseEntity<Flux<UserResponse>>> getAllUsers(ServerWebExchange exchange) {
        return Mono.just(ResponseEntity.ok(userRepository.findAll().map(UserMapper::toUserResponse)));
    }

    @Override
    @PreAuthorize("hasAnyRole('" + UserRoles.SWIMMER_READ + "')")
    public Mono<ResponseEntity<UserResponse>> getUserById(String id, ServerWebExchange exchange) {
        return userRepository
                .findById(id)
                .map(UserMapper::toUserResponse)
                .map(ResponseEntity::ok);
    }

    @Override
    @PreAuthorize("hasAnyRole('" + UserRoles.SWIMMER_WRITE + "')")
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
