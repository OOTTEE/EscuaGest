package com.escualos.domain.competition;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CompetitionSrvImpl implements CompetitionSrv {

    CompetitionRepository repository;

    @Override
    public Flux<Competition> getAll() {
        return repository.findAll();
    }

    @Override
    public Mono<Competition> getById(String id) {
        return repository.findById(id);
    }

    @Override
    public Mono<Competition> getByRef(String ref) {
        return repository.getCompetitionByReference(ref);
    }

    @Override
    public Mono<Competition> create(Competition competition) {
        return repository.insert(competition);
    }

    @Override
    public Mono<Competition> save(Competition competition) {
        return repository.save(competition);
    }

}
