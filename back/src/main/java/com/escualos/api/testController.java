package com.escualos.api;

import com.escualos.security.Roles;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.Principal;

@Controller
@RequestMapping
public class testController {

    @GetMapping("/test/noRole")
    public Mono<ResponseEntity<String>> noRole(ServerWebExchange serverWebExchange) {
        return serverWebExchange.getPrincipal().cast(JwtAuthenticationToken.class)
                .filter(AbstractAuthenticationToken::isAuthenticated) //No necesario
                .filterWhen(principal -> Flux.fromIterable(principal.getAuthorities())
                        .map(GrantedAuthority::getAuthority)
                        .map(name -> name.equals("ROLE_" + Roles.SWIMMER_READ))
                        .filter(Boolean.TRUE::equals)) // Tenemos el role asociado?
                .map(Principal::getName)//GET ID OF USER ON KEYCLOAK
                .zipWith(Mono.just("OK"))// JUNTA EL RESULTADO DEL MONO PARA RECUPERAR EL GET PRINCIPAL con el siguiente proceso
                .map(tuple2 -> tuple2.getT1().concat("____").concat(tuple2.getT2()))
                .map(ResponseEntity::ok);

    }


    @GetMapping("/test/noRole2")
    @PreAuthorize("isAuthenticated()")
    public Mono<ResponseEntity<String>> noRole2(Principal principal) {
        return Mono.just(ResponseEntity.ok("OK"));
    }

    @GetMapping("/test/noRole3")
    @PreAuthorize("hasRole('NO_ROLE')")
    public Mono<ResponseEntity<String>> noRole3(Principal principal) {
        return Mono.just(ResponseEntity.ok("OK"));
    }

}
