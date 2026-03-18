package com.homevidya.app.homevidya.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String SWAGGER_UI_PATH = "/swagger-ui/**";
    private static final String SWAGGER_UI_HTML = "/swagger-ui.html";
    private static final String API_DOCS_V3_PATH = "/v3/api-docs/**";
    private static final String API_DOCS_CUSTOM_PATH = "/api-docs/**"; // matches springdoc.api-docs.path

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(SWAGGER_UI_PATH, SWAGGER_UI_HTML, API_DOCS_V3_PATH, API_DOCS_CUSTOM_PATH).permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic( httpBasic -> httpBasic.disable());

        return http.build();
    }
}