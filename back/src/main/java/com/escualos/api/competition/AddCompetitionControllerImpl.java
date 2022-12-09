package com.escualos.api.competition;

import com.escualos.api.competition.mappers.CompetitionMapper;
import com.escualos.api.controller.AddCompetitionApi;
import com.escualos.api.model.CompetitionDTO;
import com.escualos.core.competition.CompetitionSrv;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;

@Controller
public class AddCompetitionControllerImpl implements AddCompetitionApi {
    private CompetitionSrv competitionSrv;
    private CompetitionMapper mapper;

    public AddCompetitionControllerImpl(CompetitionSrv competitionSrv, CompetitionMapper mapper) {
        this.competitionSrv = competitionSrv;
        this.mapper = mapper;
    }

    @Override
    public Mono<ResponseEntity<CompetitionDTO>> addCompetition(Mono<CompetitionDTO> competitionDTO, ServerWebExchange exchange) {
        return competitionDTO
                .map(mapper::toEntity)
                .flatMap(competitionSrv::create)
                .map(mapper::toDto)
                .map(competition -> ResponseEntity
                        .created(URI.create("/api/v2/competitions/" + competition.getReference()))
                        .build());    }
}
