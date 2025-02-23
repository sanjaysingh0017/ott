package com.example.ott.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain security(HttpSecurity httpSecurity) throws Exception {
        DefaultSecurityFilterChain securityFilterChain = httpSecurity
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                        authorizationManagerRequestMatcherRegistry
                                .requestMatchers("/login/ott").permitAll()
                                .requestMatchers("/redirect-ott-page").permitAll()
                                .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                .oneTimeTokenLogin(Customizer.withDefaults()).build();
        return securityFilterChain;
    }

    @Bean
    public InMemoryUserDetailsManager manager() {
        UserDetails userDetails = User.withUsername("test").password("{noop}test123").build();
        return new InMemoryUserDetailsManager(userDetails);
    }
}
