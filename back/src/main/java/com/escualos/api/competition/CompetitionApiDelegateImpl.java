package com.escualos.api.competition;

import com.escualos.api.controller.GetAllCompetitionsApiDelegate;
import com.escualos.core.domain.competition.CompetitionSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class CompetitionApiDelegateImpl implements GetAllCompetitionsApiDelegate {
    @Autowired
    private CompetitionSrv competitionSrv;
    @Override
    public Mono<ResponseEntity<Page>> getAllCompetitions(Pageable pageable, ServerWebExchange exchange) {
        return competitionSrv.getPaginated(pageable).map(ResponseEntity::ok);
    }

}
