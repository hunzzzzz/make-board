package com.example.board.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod; // HttpMethod 임포트 추가
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.board.common.auth.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	private final JwtAuthenticationFilter jwtAuthenticationFilter;

	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .formLogin(form -> form.disable())
            .httpBasic(httpBasic -> httpBasic.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers(
                    "/",
                    "/signup",
                    "/login",
                    "/posts",
                    "/posts/{postId}",
                    "/posts/add",
                    "/posts/{postId}/edit",
                    "/error-page",
                    
                    "/signup.html",
                    "/login.html",
                    "/posts.html",
                    "/post.html",
                    "/post-form.html",
                    "/post-edit-form.html",
                    "/error-page.html",
                    
                    "/css/**",
                    "/icons/**",
                    "/components/**",
                    "/favicon.ico",
                    "/.well-known/**"
                ).permitAll()
                .requestMatchers(HttpMethod.POST, "/api/signup/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/login/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/posts").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/posts/{postId}").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/posts/{postId}/like/check").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/posts/{postId}/comments").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}