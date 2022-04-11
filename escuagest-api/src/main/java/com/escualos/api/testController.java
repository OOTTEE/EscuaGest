package com.escualos.api;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

import javax.annotation.security.RolesAllowed;

@Controller
public class testController {

    @GetMapping("/test/noRole")
    @RolesAllowed("NO_ROLE")
    public Mono<ResponseEntity<String>> noRole() {
        return Mono.just(ResponseEntity.ok("OK"));
    }



}
