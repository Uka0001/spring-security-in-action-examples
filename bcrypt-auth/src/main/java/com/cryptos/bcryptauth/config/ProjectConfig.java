package com.cryptos.bcryptauth.config;

import com.cryptos.bcryptauth.service.AuthenticationProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

@Configuration
public class ProjectConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SCryptPasswordEncoder sCryptPasswordEncoder() {
        return new SCryptPasswordEncoder(65536, 8, 1, 32, 16);
    }

    public void configure(AuthenticationManagerBuilder auth, AuthenticationProviderService authenticationProviderService) {
        auth.authenticationProvider(authenticationProviderService);
    }

    public void configureHttpSecurity(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .formLogin(
                        form -> form
                                .defaultSuccessUrl("/main", true)
                )
                .authorizeHttpRequests(
                        authorize -> authorize
                                .requestMatchers("/main").authenticated()
                )
                .build();
    }
}
