package com.escualos.api.competition;

import com.escualos.api.competition.mappers.CompetitionMapper;
import com.escualos.api.controller.UpdateCompetitionApiDelegate;
import com.escualos.api.model.CompetitionDTO;
import com.escualos.core.domain.competition.CompetitionSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class UpdateCompetitionApiDelegateImpl implements UpdateCompetitionApiDelegate {
    @Autowired
    private CompetitionSrv competitionSrv;

    @Override
    public Mono<ResponseEntity<CompetitionDTO>> updateCompetition(Mono<CompetitionDTO> competitionDTO, ServerWebExchange exchange) {
        return competitionDTO
                .map(CompetitionMapper.INSTANCE::toEntity)
                .flatMap(competitionDTO1 -> competitionSrv.save(competitionDTO1))
                .map(CompetitionMapper.INSTANCE::toDto)
                .map(ResponseEntity::ok);
    }
}
