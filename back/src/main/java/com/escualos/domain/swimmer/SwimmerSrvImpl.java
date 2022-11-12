package com.escualos.domain.swimmer;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class SwimmerSrvImpl implements SwimmerSrv {

    private SwimmerRepository swimmerRepository;

    public SwimmerSrvImpl(SwimmerRepository swimmerRepository) {
        this.swimmerRepository = swimmerRepository;
    }

    @Override
    public Flux<Swimmer> getAllSwimmers() {
        return null;
    }

    @Override
    public Mono<Swimmer> createSwimmer(Swimmer swimmer) {
        return swimmerRepository.save(swimmer);
    }

    @Override
    public Mono<Swimmer> getSwimmer(String username) {
        return swimmerRepository.findByUsername(username);
    }
}
