package com.vet.auth.vet_auth_service.security.jwt;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component	
public class JwtProvider {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private int expiration;
    
    @Value("${jwt.refresh-token-extra-time-expiration}")
    private int refreshTokenExtraTimeExpiration;

    private String generateToken(UserDetails userDetails, Date expDate) {
      return Jwts.builder()
          .setSubject(userDetails.getUsername())
          .claim("authorities", userDetails.getAuthorities())
          .setIssuedAt(new Date())
          .setExpiration(expDate)
          .signWith(getKey(secret))
          .compact();
    }
    public String generateJwt(UserDetails userDetails) {
      return generateToken(userDetails, new Date(System.currentTimeMillis() + expiration* 1000));
    }
    public String generateRefreshToken(UserDetails userDetails) {
      return generateToken(userDetails, new Date(System.currentTimeMillis() + (expiration + refreshTokenExtraTimeExpiration)* 1000));
    }
    private Key getKey(String secret) {
      byte[] decodedKey = Decoders.BASE64.decode(secret);
      return Keys.hmacShaKeyFor(decodedKey);
    }

    public Claims getClaims(String token) {
      return Jwts.parserBuilder()
          .setSigningKey(getKey(secret))
          .build()
          .parseClaimsJws(token)
          .getBody();
    }

    public String getSubject(String token) {
      return getClaims(token).getSubject();
    }
}
