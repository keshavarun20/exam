package com.example.exam.security.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    private static SecretKey getSigningKey(){
        byte [] keyBytes= Decoders.BASE64.decode("c2VjcmV0X2tleV9leGFtcGxlXzEyMzQ1Njc4OTAxMjM0NTY3ODkwIQ==");
        return Keys.hmacShaKeyFor(keyBytes);
    }
    
    public static String generateToken(User user){
        Collection<GrantedAuthority> authorities = user.getAuthorities();  // Get authorities from UserDetails

        List<String> roles = authorities.stream()
                .map(GrantedAuthority::getAuthority)  // Extract role names (e.g., ROLE_USER)
                .collect(Collectors.toList());  // Collect them as a list of strings

        return Jwts.builder()
                .subject(user.getUsername())
                .claim("roles", roles)  // Add roles claim as a list of strings
                .expiration(new Date(System.currentTimeMillis() + 300_000))  // Set expiration time
                .signWith(getSigningKey())
                .compact();


    }

    public static Claims getClaims(String token){
        try {
            return Jwts
                    .parser()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

        }catch (Exception e){
            throw new RuntimeException("Invalid Token ",e);
        }
    }

    public static boolean isTokenValid(String token){
        return !isExpired(token);
    }

    private static boolean isExpired(String token){
        return getClaims(token)
                .getExpiration()
                .before(new Date());
    }
}
