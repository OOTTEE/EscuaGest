package com.escualos.api.swimmer;

import com.escualos.api.SwimmerApiDelegate;
import com.escualos.Roles;
import com.escualos.domain.swimmer.SwimmerSrv;
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

    public SwimmerApiDelegateImpl(SwimmerSrv swimmerSrv) {
        this.swimmerSrv = swimmerSrv;
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
        return createSwimmerDTO
            .map(SwimmerMapper::toSwimmer)
            .flatMap(swimmerSrv::createSwimmer)
            .map(SwimmerMapper::toSwimmerDTO)
            .map(swimmer -> ResponseEntity
                    .created(URI.create("/swimmer/%s".formatted(swimmer.getUsername())))
                    .body(swimmer));
    }



//    @Override
//    @PreAuthorize("hasAnyRole('" + UserRoles.SWIMMER_READ + "')")
//    public Mono<ResponseEntity<Flux<UserResponse>>> getAllUsers(ServerWebExchange exchange) {
//        return Mono.just(ResponseEntity.ok(userSrv.getAllUsers().map(UserMapper::toUserResponse)));
//    }
//
//    @Override
//    @PreAuthorize("hasAnyRole('" + UserRoles.SWIMMER_READ + "')")
//    public Mono<ResponseEntity<UserResponse>> getUserById(String id, ServerWebExchange exchange) {
//        return userSrv.getUserById(id)
//                .map(UserMapper::toUserResponse)
//                .map(ResponseEntity::ok);
//    }
//
//    @Override
//    @PreAuthorize("hasAnyRole('" + UserRoles.SWIMMER_WRITE + "')")
//    public Mono<ResponseEntity<UserResponse>> postNewUser(Mono<CreateUserRequest> createUserRequest, ServerWebExchange exchange) {
//        return createUserRequest
//            .map(UserMapper::fromCreateUserRequest)
//            .flatMap(userSrv::createUser)
//            .map(user -> ResponseEntity.created(URI.create("/user/%s".formatted(user.getId()))).build());
//    }
}
