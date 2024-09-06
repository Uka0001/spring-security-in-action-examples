package com.cryptos.ssoclient.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Slf4j
@Controller
public class MainController {
    @GetMapping("/")
    public String mainHtml(OAuth2AuthenticationToken token) {
        Map<String, Object> attributes = token.getPrincipal().getAttributes();
        log.info("token principal name: {}, email: {}", attributes.get("name"), attributes.get("email"));
        return "main.html";
    }

    @GetMapping("/logout_success")
    public String logout() {
        log.info("logout success, redirect to logout_success.html");
        return "logout_success.html";
    }
}
