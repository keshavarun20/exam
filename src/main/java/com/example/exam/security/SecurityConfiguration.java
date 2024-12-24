package com.example.exam.security;

import com.example.exam.security.jwt.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final UserDetailsService userDetailsService;
    public JwtFilter jwtFilter() {return new JwtFilter();}

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                .build();
    }

    public SecurityConfiguration(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       return http
                .csrf(customizer -> customizer.disable()) // Disable CSRF for simplicity (not recommended for production)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/products").permitAll()
                        .requestMatchers(HttpMethod.POST, "/login").permitAll()
                        // Only authenticated users can POST (create) or PUT (update) products
                        .requestMatchers(HttpMethod.POST, "/product/create").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/product/**").authenticated()

                        // Only superuser can DELETE products
                        .requestMatchers(HttpMethod.DELETE, "/product").hasRole("ADMIN")

                        // All other requests require authentication
                        .anyRequest().authenticated()
                )
                .addFilterBefore(
                        jwtFilter(),
                        UsernamePasswordAuthenticationFilter.class
                ).build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Use BCrypt for encoding passwords
    }

}
