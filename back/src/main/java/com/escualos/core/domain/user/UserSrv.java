package com.escualos.core.domain.user;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import reactor.core.publisher.Mono;

public interface UserSrv {

    Mono<User> getUserInfo(Mono<JwtAuthenticationToken> principal);
}
