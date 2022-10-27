package com.escualos.domain.swimmer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SwimmerSrv {
    Flux<Swimmer> getAllSwimmers();

    Mono<Swimmer> createSwimmer(Swimmer swimmer);

    Mono<Swimmer> getSwimmer(String username);
}
