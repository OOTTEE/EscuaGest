package com.escualos.domain.competition;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CompetitionSrv {

    Flux<Competition> getAll();

    Mono<Competition> getById(String id);

    Mono<Competition> getByRef(String ref);

    Mono<Competition> create(Competition competition);

    Mono<Competition> save(Competition competition);
}
