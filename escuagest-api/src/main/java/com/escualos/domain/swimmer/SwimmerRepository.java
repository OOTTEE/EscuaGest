package com.escualos.domain.swimmer;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface SwimmerRepository extends ReactiveMongoRepository<Swimmer,String> {

    Mono<Swimmer> findByUsername(String username);

}
