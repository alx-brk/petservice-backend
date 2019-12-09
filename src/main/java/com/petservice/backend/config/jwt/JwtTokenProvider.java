package com.petservice.backend.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt.secret}")
    private String secret;

    @Value("${app.jwt.token.prefix}")
    private String prefix;

    @Value("${app.jwt.header.string}")
    private String header;

    @Value("${app.jwt.expiration-in-ms}")
    private Long expiration;

    public String generateToken(Authentication authentication) {
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining());

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim("roles", authorities)
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Authentication getAuthentication(HttpServletRequest request) {
        String token = resolveToken(request);
        if (token == null) {
            return null;
        }

        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();

        String userName = claims.getSubject();
        List<GrantedAuthority> authorities = Arrays.stream(claims.get("roles").toString().split(","))
                .map(role -> role.startsWith("ROLE_") ? role : "ROLE_" + role)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return (userName != null) ? new UsernamePasswordAuthenticationToken(userName, null, authorities) : null;
    }

    public boolean validateToken(HttpServletRequest request){
        String token = resolveToken(request);

        if (token == null) {
            return false;
        }

        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return !claims.getExpiration().before(new Date());

    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(header);

        if (bearerToken != null && bearerToken.startsWith(prefix)) {
            return bearerToken.substring(7, bearerToken.length());
        }

        return null;
    }
}
