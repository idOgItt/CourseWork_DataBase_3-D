package com.threed_model_market.project.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.threed_model_market.project.security.Constants.AUTHORITIES_KEY;

@Component
public class JwtTokenProvider {

    private final SecretKey jwtSecret;

    @Value("${spring.app.jwtExpirationInMs}")
    private long jwtExpirationInMs;

    public JwtTokenProvider(@Value("${spring.app.jwtSecret}") String secret) {
        this.jwtSecret = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String getEmailFromJWT(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(jwtSecret)
                    .build()
                    .parseSignedClaims(token);
            return !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(final String email, final Long userId, final Collection<? extends GrantedAuthority> authorities) {
        return Jwts.builder().subject(email)
                .claim("userId", userId)
                .claim(AUTHORITIES_KEY, authorities.stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.joining(",")))
                .signWith(jwtSecret).issuedAt(new Date(System.currentTimeMillis())).expiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
                .compact();
    }

    public UsernamePasswordAuthenticationToken getAuthentication(final String token, final Authentication existingAuth, final CustomUserDetails userDetails) {
        final Claims claims = getClaimsFromToken(token);

        final Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        List<GrantedAuthority> currentAuthorities = new ArrayList<>(existingAuth.getAuthorities());

        if (!new HashSet<>(currentAuthorities).containsAll(authorities)) {
            return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
        }

        return (UsernamePasswordAuthenticationToken) existingAuth;
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .verifyWith(jwtSecret)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public Long getUserIdFromJWT(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.get("userId", Long.class);
    }

    public Collection<GrantedAuthority> getAuthoritiesFromJWT(String token) {
        Claims claims = getClaimsFromToken(token);
        String authoritiesClaim = claims.get(AUTHORITIES_KEY, String.class);
        return Arrays.stream(authoritiesClaim.split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
