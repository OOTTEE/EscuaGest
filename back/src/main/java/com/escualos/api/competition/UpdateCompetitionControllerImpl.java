package com.escualos.api.competition;

import com.escualos.api.competition.mappers.CompetitionMapper;
import com.escualos.api.controller.UpdateCompetitionApi;
import com.escualos.api.model.CompetitionDTO;
import com.escualos.core.competition.CompetitionSrv;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Controller
public class UpdateCompetitionControllerImpl implements UpdateCompetitionApi {
    private final CompetitionSrv competitionSrv;
    private final CompetitionMapper mapper;

    public UpdateCompetitionControllerImpl(CompetitionSrv competitionSrv, CompetitionMapper mapper) {
        this.competitionSrv = competitionSrv;
        this.mapper = mapper;
    }

    @Override
    public Mono<ResponseEntity<CompetitionDTO>> updateCompetition(Mono<CompetitionDTO> competitionDTO, ServerWebExchange exchange) {
        return competitionDTO
                .map(mapper::toEntity)
                .flatMap(competitionDTO1 -> competitionSrv.save(competitionDTO1))
                .map(mapper::toDto)
                .map(ResponseEntity::ok);
    }
}
