package com.cafegory.api.security;

import com.cafegory.auth.implement.JwtTokenManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtTokenManager jwtTokenManager;
    private final MemberDetailsService memberDetailsService;
    private final AuthenticationEntryPoint authEntryPoint;

    public SecurityConfig(JwtTokenManager jwtTokenManager, MemberDetailsService memberDetailsService,
                          @Qualifier("jwtTokenAuthenticationEntrypoint") AuthenticationEntryPoint authenticationEntryPoint) {
        this.jwtTokenManager = jwtTokenManager;
        this.memberDetailsService = memberDetailsService;
        this.authEntryPoint = authenticationEntryPoint;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //JWT 토큰 검증을 하지 않으려면 anyMatchers에 url을 추가하고 JwtAuthenticationFilter 클래스 안에도 추가해야한다.
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/favicon.ico").permitAll()
                        .requestMatchers("/docs/**").permitAll()
                        .requestMatchers("/login/**").permitAll()
                        .requestMatchers("/auth/refresh").permitAll()
                        .requestMatchers(HttpMethod.GET, "/cafe-studies/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/cafes/**").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenManager, memberDetailsService),
                        UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exceptionHandling -> exceptionHandling.
                        authenticationEntryPoint(authEntryPoint))
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(
                List.of("http://localhost:5173")
        );
        configuration.setAllowedMethods(
                List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
        );
        configuration.setAllowedHeaders(
                List.of("Authorization", "Content-Type")
        );
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
