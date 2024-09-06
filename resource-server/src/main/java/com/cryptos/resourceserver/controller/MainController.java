package com.cryptos.resourceserver.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MainController {
    @GetMapping("/hello")
    public String hello() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("authentication: {}", authentication);

        if (authentication.getPrincipal() instanceof Jwt jwt) {
            jwt.getClaims().forEach((k, v) -> log.info("Claim: {} {}", k, v));
            String username = jwt.getClaimAsString("name");
            String email = jwt.getClaimAsString("email");
            log.info("User '{}' with email '{}' accessed /hello endpoint", username, email);
        } else {
            log.warn("Principal is not an instance of Jwt");
        }
        return "Hello! Resourse server works!";
    }
}
