package org.example.spring_security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security){

        security.csrf(customizer -> customizer.disable());
        security.authorizeHttpRequests(request -> request.anyRequest().authenticated());
        //security.formLogin(Customizer.withDefaults()); // Login form for browsers
        security.httpBasic(Customizer.withDefaults()); // Login form for postman or rest APIs
        security.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return security.build();
    }
}
