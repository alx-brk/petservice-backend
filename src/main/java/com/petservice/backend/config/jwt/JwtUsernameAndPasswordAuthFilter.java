//package com.petservice.backend.config.jwt;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.http.HttpHeaders;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.time.LocalDate;
//import java.util.Date;
//
//public class JwtUsernameAndPasswordAuthFilter extends UsernamePasswordAuthenticationFilter {
//
//
//    private AuthenticationManager authenticationManager;
//    private ObjectMapper objectMapper;
//    private long tokenExpiration;
//    private String secret;
//    private String prefix;
//
//    public JwtUsernameAndPasswordAuthFilter(AuthenticationManager authenticationManager, ObjectMapper objectMapper, long tokenExpiration, String secret, String prefix) {
//        this.authenticationManager = authenticationManager;
//        this.objectMapper = objectMapper;
//        this.tokenExpiration = tokenExpiration;
//        this.secret = secret;
//        this.prefix = prefix;
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//
//        try {
//            UsernameAndPasswordAuthRequest usernameAndPasswordAuthRequest = objectMapper.readValue(request.getInputStream(), UsernameAndPasswordAuthRequest.class);
//
//            Authentication authentication = new UsernamePasswordAuthenticationToken(
//                    usernameAndPasswordAuthRequest.getUsername(),
//                    usernameAndPasswordAuthRequest.getPassword()
//            );
//
//            return authenticationManager.authenticate(authentication);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
//        String token = Jwts.builder()
//                .setSubject(authResult.getName())
//                .claim("authorities", authResult.getAuthorities())
//                .setIssuedAt(new Date())
//                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(tokenExpiration)))
//                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
//                .compact();
//
//        response.addHeader(HttpHeaders.AUTHORIZATION, prefix + " " + token);
//    }
//}
