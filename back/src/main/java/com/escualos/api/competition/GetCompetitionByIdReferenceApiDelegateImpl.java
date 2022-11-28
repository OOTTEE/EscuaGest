package com.escualos.api.competition;

import com.escualos.api.competition.mappers.CompetitionMapper;
import com.escualos.api.controller.GetCompetitionByIdReferenceApiDelegate;
import com.escualos.api.model.CompetitionDTO;
import com.escualos.core.domain.competition.CompetitionSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class GetCompetitionByIdReferenceApiDelegateImpl implements GetCompetitionByIdReferenceApiDelegate {
    @Autowired
    private CompetitionSrv competitionSrv;
    @Override
    public Mono<ResponseEntity<CompetitionDTO>> getCompetitionsByReference(String referenceId, ServerWebExchange exchange) {
        return competitionSrv.getByRef(referenceId)
                .map(CompetitionMapper.INSTANCE::toDto)
                .map(ResponseEntity::ok);
    }

}
