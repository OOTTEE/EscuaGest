package com.escualos.domain.user;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;


@Component
public class UserSrvImpl implements UserSrv {

    @Override
    public Mono<User> getUserInfo(Mono<JwtAuthenticationToken> principal) {
        return principal.map(jwtAuthenticationToken -> {
            final Jwt credentials = (Jwt) jwtAuthenticationToken.getCredentials();
            return User.builder()
                    .id(credentials.getId())
                    .username((String) credentials.getClaims().get("preferred_username"))
                    .fullname((String) credentials.getClaims().get("name"))
                    .firstName((String) credentials.getClaims().get("given_name"))
                    .lastsNames((String) credentials.getClaims().get("family_name"))
                    .email((String) credentials.getClaims().get("email"))
                    .emailVerified((boolean) credentials.getClaims().get("email_verified"))
                    .build();
        });
    }

}
