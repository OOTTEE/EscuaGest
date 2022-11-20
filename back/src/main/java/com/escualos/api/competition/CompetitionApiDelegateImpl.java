package com.escualos.api.competition;

import com.escualos.api.CompetitionsApiDelegate;
import com.escualos.domain.competition.CompetitionSrv;
import com.escualos.model.CompetitionDTO;
import com.escualos.model.GetAllCompetitions200Response;
import com.escualos.model.SortDirectionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;

public class CompetitionApiDelegateImpl implements CompetitionsApiDelegate {

    @Autowired
    private CompetitionSrv competitionSrv;

    @Override
    public Mono<ResponseEntity<CompetitionDTO>> addCompetition(Mono<CompetitionDTO> competitionDTO, ServerWebExchange exchange) {
        return competitionDTO
                .map(CompetitionMapper.INSTANCE::toEntity)
                .flatMap(competitionSrv::create)
                .map(competition -> ResponseEntity
                        .created(URI.create("/api/v2/competitions/" + competition.getReference()))
                        .build());
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
    public Mono<ResponseEntity<CompetitionDTO>> updateCompetitionByReference(Mono<CompetitionDTO> competitionDTO, ServerWebExchange exchange) {
        return CompetitionsApiDelegate.super.updateCompetitionByReference(competitionDTO, exchange);
    }
}
