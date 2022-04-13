package com.escualos.api;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

import java.security.Principal;

@Controller
@RequestMapping
public class testController {

    @GetMapping("/test/noRole")
    public Mono<ResponseEntity<String>> noRole(Principal principal) {
        return Mono.just(ResponseEntity.ok("OK"));
    }


    @GetMapping("/test/noRole2")
    @PreAuthorize("isAuthenticated()")
    public Mono<ResponseEntity<String>> noRole2(Principal principal) {
        return Mono.just(ResponseEntity.ok("OK"));
    }

    @GetMapping("/test/noRole3")
    @PreAuthorize("hasRole('NO_ROLE_2')")
    public Mono<ResponseEntity<String>> noRole3(Principal principal) {
        return Mono.just(ResponseEntity.ok("OK"));
    }

}
