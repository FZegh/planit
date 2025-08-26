package com.descodeuses.planit.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${allowCorsOrigin}")
    private String allowCorsOrigin;

    
@Autowired
    private JwtFilter jwtFilter;


    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    return config.getAuthenticationManager();
}

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http, CorsConfigurationSource corsConfigurationSource) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) //  CSRF désactivé pour les tests et Postman
            .cors(cors -> cors.configurationSource(corsConfigurationSource)) // 🌍 CORS activé
            .authorizeHttpRequests(auth -> auth
               .requestMatchers("/auth/**").permitAll()     // AuthController : obligatoire pour avoir l'autorisation(page publique)
               .requestMatchers("/api/**").hasAnyRole("USER", "ADMIN") //hasAnyRole : personne peut acceder si il n'a pas le role USER ou ADMIN
                .anyRequest().authenticated())
            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));                                  //.anyRequest().permitAll() // 🔓 autorise tous les endpoints
            
            http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowedOrigins(List.of(allowCorsOrigin.split(","))); // 🌐 Frontend Angular
        config.setAllowedMethods(List.of("*")); // 
        config.setAllowedHeaders(List.of("*")); // Autorise tous les headers
        config.setAllowCredentials(true); // Cookies et autorisations cross-domain

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); // Appliquer à toutes les routes
        return source;
    }
}
