package com.escualos.domain.user;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserSrv {
    public Flux<User> getAllUsers();

    public Mono<User> getUserById(String userId);

    Mono<User> getUserByUsername(String username);

    public Mono<User> createUser(User user);

}
