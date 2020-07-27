package com.petservice.backend.config.jwt;


import com.petservice.backend.model.dto.UserDetailsImpl;
import com.petservice.backend.persistence.entity.User;
import com.petservice.backend.persistence.repository.UserRepository;
import com.petservice.backend.services.exceptions.ForbiddenException;
import com.petservice.backend.services.exceptions.NotFoundException;
import com.petservice.backend.services.validation.ValidationUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtTokenUtils implements Serializable {


    @Value("${app.jwt.secret}")
    private String secret;

    @Value("${app.jwt.expiration-in-weeks}")
    private long tokenExpiration;

    @Autowired
    private UserRepository userRepository;

    public String getUsernameFromToken(String token) {

        return getClaimFromToken(token, Claims::getSubject);

    }


    public Date getExpirationDateFromToken(String token) {

        return getClaimFromToken(token, Claims::getExpiration);

    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {

        final Claims claims = getAllClaimsFromToken(token);

        return claimsResolver.apply(claims);

    }


    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


    private Boolean isTokenExpired(String token) {

        final Date expiration = getExpirationDateFromToken(token);

        return expiration.before(new Date());

    }


    public String generateToken(UserDetailsImpl userDetails) {

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("authorities", userDetails.getAuthorities())
                .claim("id", userDetails.getId())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(tokenExpiration)))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();

    }

    public Set<GrantedAuthority> getGrantedAuthorities(String token){
        List<Map<String, String>> authorities = (List<Map<String, String>>)getClaimFromToken(token, claims -> claims.get("authorities"));

        return authorities.stream()
                .map(item -> new SimpleGrantedAuthority(item.get("authority")))
                .collect(Collectors.toSet());
    }

    public Long getUserId(String token){
        String id = getClaimFromToken(token, claims -> claims.get("id")).toString();
        return Long.valueOf(id) ;
    }


    public void validateAccess(Long userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> NotFoundException.builder()
                .entity(User.class)
                .message(ValidationUtils.USER_NOT_FOUND)
                .object(userId)
                .build());

        if (SecurityContextHolder.getContext().getAuthentication() != null){
            if (!SecurityContextHolder.getContext().getAuthentication().getName().equals(user.getEmail())) {
                throw new ForbiddenException(ValidationUtils.FORBIDDEN);
            }
        }
    }


    public void validateToken(String token) {

        if (isTokenExpired(token)){
            throw new ForbiddenException(ValidationUtils.EXPIRED);
        }

        Long id = getUserId(token);

        if (id != null){
            validateAccess(id);
        }

    }

}
