package com.example.exam.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class JwtFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String token = null;


        if (authHeader != null && authHeader.startsWith("Bearer ")){
            token = authHeader.substring(7);
        }

        if (token != null && JwtUtil.isTokenValid(token)){

            String username = JwtUtil.getClaims(token).getSubject();
            List<String> roles = JwtUtil.getClaims(token).get("roles", List.class);

            // Convert roles to GrantedAuthorities
            List<SimpleGrantedAuthority> authorities = roles.stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role))  // Prefix with 'ROLE_' if needed
                    .collect(Collectors.toList());

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }
}
