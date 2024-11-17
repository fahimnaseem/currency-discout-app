package com.example.currency_discount_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
            http
                    .csrf(csrf -> csrf.disable()) // Disable CSRF (only for testing purposes)
                    .authorizeHttpRequests(auth -> auth
                            .anyRequest().permitAll() // Allow all requests without authentication
                    );
            return http.build();
        }
}
