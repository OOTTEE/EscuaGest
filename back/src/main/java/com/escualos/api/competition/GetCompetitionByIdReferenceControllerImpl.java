package com.escualos.api.competition;

import com.escualos.api.competition.mappers.CompetitionMapper;
import com.escualos.api.controller.GetCompetitionByIdReferenceApi;
import com.escualos.api.model.CompetitionDTO;
import com.escualos.core.competition.CompetitionSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Controller
public class GetCompetitionByIdReferenceControllerImpl implements GetCompetitionByIdReferenceApi {
    @Autowired
    private CompetitionSrv competitionSrv;
    @Autowired
    private CompetitionMapper mapper;
    @Override
    public Mono<ResponseEntity<CompetitionDTO>> getCompetitionsByReference(String referenceId, ServerWebExchange exchange) {
        return competitionSrv.getByRef(referenceId)
                .map(mapper::toDto)
                .map(ResponseEntity::ok);
    }

}
