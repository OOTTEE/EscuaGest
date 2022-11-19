package com.escualos.api.competition;

import org.openapitools.api.CompetitionsApiDelegate;
import org.openapitools.model.CompetitionDTO;
import org.openapitools.model.GetAllCompetitions200Response;
import org.openapitools.model.SortDirectionEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class CompetitionApiDelegateImpl implements CompetitionsApiDelegate {
    @Override
    public Mono<ResponseEntity<CompetitionDTO>> addCompetition(ServerWebExchange exchange) {
        return CompetitionsApiDelegate.super.addCompetition(exchange);
    }

    @Override
    public Mono<ResponseEntity<GetAllCompetitions200Response>> getAllCompetitions(Integer offset, Integer limit, String sortBy, SortDirectionEnum direction, ServerWebExchange exchange) {
        return CompetitionsApiDelegate.super.getAllCompetitions(offset, limit, sortBy, direction, exchange);
    }

    @Override
    public Mono<ResponseEntity<CompetitionDTO>> getCompetitionsByReference(String referenceId, ServerWebExchange exchange) {
        return CompetitionsApiDelegate.super.getCompetitionsByReference(referenceId, exchange);
    }

    @Override
    public Mono<ResponseEntity<CompetitionDTO>> updateCompetitionByReference(ServerWebExchange exchange) {
        return CompetitionsApiDelegate.super.updateCompetitionByReference(exchange);
    }
}
