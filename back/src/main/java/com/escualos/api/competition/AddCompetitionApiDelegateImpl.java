package com.escualos.api.competition;

import com.escualos.api.competition.mappers.CompetitionMapper;
import com.escualos.api.controller.AddCompetitionApiDelegate;
import com.escualos.api.model.CompetitionDTO;
import com.escualos.core.domain.competition.CompetitionSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;

public class AddCompetitionApiDelegateImpl implements AddCompetitionApiDelegate {
    @Autowired
    private CompetitionSrv competitionSrv;
    @Override
    public Mono<ResponseEntity<CompetitionDTO>> addCompetition(Mono<CompetitionDTO> competitionDTO, ServerWebExchange exchange) {
        return competitionDTO
                .map(CompetitionMapper.INSTANCE::toEntity)
                .flatMap(competitionSrv::create)
                .map(CompetitionMapper.INSTANCE::toDto)
                .map(competition -> ResponseEntity
                        .created(URI.create("/api/v2/competitions/" + competition.getReference()))
                        .build());    }
}
