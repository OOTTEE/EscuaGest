package com.escualos.domain.user;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserSrv {
    public Flux<User> getAllUsers();

    public Mono<User> getUserById(String userId);

    public Mono<User> createUser(User user);

}
