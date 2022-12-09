package com.escualos.core.competition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CompetitionSrvImpl implements CompetitionSrv {

    @Autowired
    CompetitionRepository repository;

    @Override
    public Flux<Competition> getAll() {
        return repository.findAll();
    }

    @Override
    public Mono<Page<Competition>> getPaginated(Pageable pageable) {
        return repository.findAll()
                .skip((long) pageable.getPageSize() * pageable.getPageNumber())
                .take(pageable.getPageSize())
                .collectList()
                .flatMap(competitions -> repository.count().map(count -> new PageImpl<>(competitions, pageable, count)));
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
