package com.escualos.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;

/**
 * This class extract the roles set on the realm_access field on JWT token
 */
public class ReactiveKeycloakRealmRoleConverter implements Converter<Jwt, Flux<GrantedAuthority>> {
    @Override
    public Flux<GrantedAuthority> convert(Jwt jwt) {
        final Map<String, Object> realmAccess = (Map<String, Object>) jwt.getClaims().get("realm_access");
        return Flux.fromIterable((List<String>)realmAccess.get("roles"))
                .map(roleName -> "ROLE_" + roleName) // prefix to map to a Spring Security "role"
                .map(SimpleGrantedAuthority::new);
    }
}
