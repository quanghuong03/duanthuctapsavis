//package com.example.poloman.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//import java.util.List;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.cors().configurationSource(corsConfigurationSource()).and().csrf().disable()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS.STATELESS);
//        http.authorizeRequests()
//                .requestMatchers(HttpMethod.GET, "/*", "/**").permitAll()
//                .requestMatchers(HttpMethod.POST, "/*", "/**").permitAll()
//                .requestMatchers(HttpMethod.PUT, "/*", "/**").permitAll()
//                .requestMatchers(HttpMethod.PATCH, "/*", "/**").permitAll()
//                .requestMatchers(HttpMethod.DELETE, "/*", "/**").permitAll()
//                .anyRequest().authenticated();
//
//
//
//        return http.build();
//    }
//
//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(List.of("*"));
//        configuration.setAllowedMethods(List.of("GET","POST","PUT","PATCH","DELETE"));
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
//}
