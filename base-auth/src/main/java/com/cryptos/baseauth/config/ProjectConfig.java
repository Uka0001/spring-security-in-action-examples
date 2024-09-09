package com.cryptos.baseauth.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableAsync
public class ProjectConfig {

    @Autowired
    private CustomAuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private CustomAuthenticationFailureHandler authenticationFailureHandler;

    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .formLogin(
                        formLogin -> formLogin
                                .failureHandler(authenticationFailureHandler)
                                .successHandler(authenticationSuccessHandler)
                )
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(
                        authorizeRequests -> authorizeRequests
                                .requestMatchers("/hello").authenticated()
                                .anyRequest().authenticated()
                )
                .build();

    }

//    @Autowired
//    private AuthenticationProvider authenticationProvider;

//    protected void configure(AuthenticationManagerBuilder auth) {
//        auth.authenticationProvider(authenticationProvider);
//    }

    public InitializingBean initializingBean() {
        return () -> SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
        /*
         * Can be managed with set StrategyName:
         * MODE_THREADLOCAL,
         * MODE_INHERITABLETHREADLOCAL,
         * MODE_GLOBAL
         * */
    }

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("noop", NoOpPasswordEncoder.getInstance()); // NoOpPasswordEncoder is deprecated
        encoders.put("bcrypt", new BCryptPasswordEncoder());
        encoders.put("scrypt", new SCryptPasswordEncoder(16384, 8, 1, 32, 64));
        return new DelegatingPasswordEncoder("bcrypt", encoders);
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }
    /*
     * Possible Spring Security PasswordEncoder implementations:
     * NoOpPasswordEncoder
     * BCryptPasswordEncoder
     * Pbkdf2PasswordEncoder
     * StandardPasswordEncoder
     * SCryptPasswordEncoder
     * */
}
