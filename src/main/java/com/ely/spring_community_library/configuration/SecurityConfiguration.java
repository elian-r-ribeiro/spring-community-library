package com.ely.spring_community_library.configuration;

import com.ely.spring_community_library.filters.SecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(
                        SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.DELETE, "/users/*").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/users/*").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.GET, "/users", "/users/*").hasRole("MANAGER")

                        .requestMatchers(HttpMethod.POST, "/books").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/books/*").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/books/*").hasRole("MANAGER")

                        .requestMatchers(HttpMethod.POST, "/loans").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/loans/*").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/loans/*").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.GET, "/loans", "/loans/*").hasRole("MANAGER")

                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .anyRequest().authenticated()
                ).addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
