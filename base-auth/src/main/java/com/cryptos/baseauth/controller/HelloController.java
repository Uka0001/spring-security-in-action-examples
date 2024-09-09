package com.cryptos.baseauth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication auth = context.getAuthentication();
        return "Hello, " + auth.getName() + "!";
    }

    @GetMapping("/hello-injected")
    public String helloWithInjectedAuth(Authentication auth) {
        return "Hello, " + auth.getName() + "!";
    }

    @GetMapping("/hello-async")
    @Async
    public void helloAsync(Authentication auth) {
        log.info( "Hello, " + auth.getName() + "!");
    }
}
