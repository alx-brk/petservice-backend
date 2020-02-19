package com.petservice.backend.config;

import com.petservice.backend.config.enums.UserRole;
import com.petservice.backend.config.jwt.JwtAuthenticationEntryPoint;
import com.petservice.backend.config.jwt.JwtTokenVerifierFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${app.jwt.secret}")
    private String secret;

    @Value("${app.jwt.token.prefix}")
    private String prefix;

    @Value("${app.jwt.expiration-in-weeks}")
    private Long expiration;

    @Autowired
    private UserDetailsService userDetailsServiceImpl;

    @Autowired
    private JwtTokenVerifierFilter jwtTokenVerifierFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public JwtAuthenticationEntryPoint jwtAuthenticationEntryPointBean() throws Exception {
        return new JwtAuthenticationEntryPoint();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("*")
                        .allowCredentials(true)
                        .allowedOrigins("http://localhost:8080");
            }
        };
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtTokenVerifierFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers(
                        "/animal/all",
                        "/city/all",
                        "/feedback/all",
                        "/user",
                        "/user/search",
                        "/image/**",
                        "/pet-service/all",
                        "/",
                        "/job/search",
                        "/job/units/all",
                        "/job/units/all",
                        "/job/statuses/all",
                        "/auth/**",
                        "/swagger-ui.html#",
                        "/swagger-ui.html#/**"
                ).permitAll()   //TODO: looks like it doesn't work. still require baic auth
                .antMatchers(
                        "/job",
                        "/job/client-orders",
                        "/job/petsitter-orders"
                ).hasRole(UserRole.USER_ROLE.name())
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPointBean());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
    }
}
