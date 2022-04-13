package com.escualos.domain.user;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class UserSrvImpl implements UserSrv {

    private UserRepository userRepository;

    public UserSrvImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Flux<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Mono<User> getUserById(String userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Mono<User> createUser(User user) {

        return userRepository.save(user);
    }
}
