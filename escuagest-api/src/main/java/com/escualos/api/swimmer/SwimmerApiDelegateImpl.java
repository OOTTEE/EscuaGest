package com.escualos.api.swimmer;

import com.escualos.api.SwimmerApiDelegate;
import com.escualos.security.Roles;
import com.escualos.domain.swimmer.SwimmerSrv;
import com.escualos.domain.user.UserSrv;
import com.escualos.model.CreateSwimmerDTO;
import com.escualos.model.SwimmerDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
public class SwimmerApiDelegateImpl implements SwimmerApiDelegate {

    private SwimmerSrv swimmerSrv;
    private UserSrv userSrv;

    public SwimmerApiDelegateImpl(SwimmerSrv swimmerSrv, UserSrv userSrv) {
        this.swimmerSrv = swimmerSrv;
        this.userSrv = userSrv;
    }

    @PreAuthorize("hasAnyRole('" + Roles.SWIMMER_READ + "')")
    @Override
    public Mono<ResponseEntity<Flux<SwimmerDTO>>> getAllSwimmers(ServerWebExchange exchange) {
        return Mono.just(ResponseEntity.ok(swimmerSrv.getAllSwimmers().map(SwimmerMapper::toSwimmerDTO)));
    }

    @PreAuthorize("hasAnyRole('" + Roles.SWIMMER_READ + "')")
    @Override
    public Mono<ResponseEntity<SwimmerDTO>> getSwimmerByUsername(String username, ServerWebExchange exchange) {
        return swimmerSrv.getSwimmer(username)
                .map(SwimmerMapper::toSwimmerDTO)
                .map(ResponseEntity::ok);
    }

    @PreAuthorize("hasAnyRole('" + Roles.SWIMMER_WRITE + "','" + Roles.SWIMMER_ADMIN + "')")
    @Override
    public Mono<ResponseEntity<SwimmerDTO>> postNewUser(Mono<CreateSwimmerDTO> createSwimmerDTO, ServerWebExchange exchange) {
        // TODO Solo un nadador puede crear su propio usuario
        //      Un Administrador o un Manager puede crear otro usuario
        return createSwimmerDTO
            .map(SwimmerMapper::toSwimmer)
            .flatMap(swimmerSrv::createSwimmer)
            .map(SwimmerMapper::toSwimmerDTO)
            .map(swimmer -> ResponseEntity
                    .created(URI.create("/swimmer/%s".formatted(swimmer.getUsername())))
                    .body(swimmer));
    }


}
