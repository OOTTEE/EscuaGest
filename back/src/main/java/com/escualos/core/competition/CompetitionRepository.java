package com.escualos.core.competition;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface CompetitionRepository extends ReactiveMongoRepository<Competition, String> {

    Mono<Competition> getCompetitionByReference(String ref);

}
