package com.escualos.core.domain.competition;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CompetitionSrv {

    Flux<Competition> getAll();

    Mono<Page<Competition>> getPaginated(Pageable pageable);

    Mono<Competition> getById(String id);

    Mono<Competition> getByRef(String ref);

    Mono<Competition> create(Competition competition);

    Mono<Competition> save(Competition competition);
}
