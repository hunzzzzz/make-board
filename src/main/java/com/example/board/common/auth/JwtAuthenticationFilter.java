package com.example.board.common.auth;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.board.common.component.JwtProvider;
import com.example.board.common.exception.ErrorCode;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	private final JwtProvider jwtProvider;

	private static final List<RequestMatcher> EXCLUDE_REQUEST_MATCHERS = Arrays.asList(
            // 매핑 경로
            PathPatternRequestMatcher.withDefaults().matcher("/"),
            PathPatternRequestMatcher.withDefaults().matcher("/signup"),
            PathPatternRequestMatcher.withDefaults().matcher("/login"),
            PathPatternRequestMatcher.withDefaults().matcher("/posts"),
            PathPatternRequestMatcher.withDefaults().matcher("/posts/{postId}"),
            PathPatternRequestMatcher.withDefaults().matcher("/posts/add"),
            PathPatternRequestMatcher.withDefaults().matcher("/posts/{postId}/edit"),

            // 인증이 불필요한 API 호출
            PathPatternRequestMatcher.withDefaults().matcher(HttpMethod.POST, "/api/signup/**"),
            PathPatternRequestMatcher.withDefaults().matcher(HttpMethod.POST, "/api/login/**"),
            PathPatternRequestMatcher.withDefaults().matcher(HttpMethod.GET, "/api/posts"),
            PathPatternRequestMatcher.withDefaults().matcher(HttpMethod.GET, "/api/posts/{postId}"),
            PathPatternRequestMatcher.withDefaults().matcher(HttpMethod.GET, "/api/posts/{postId}/like/check"),

            // 매핑 HTML 파일
            PathPatternRequestMatcher.withDefaults().matcher("/signup.html"),
            PathPatternRequestMatcher.withDefaults().matcher("/login.html"),
            PathPatternRequestMatcher.withDefaults().matcher("/posts.html"),
            PathPatternRequestMatcher.withDefaults().matcher("/post.html"),
            PathPatternRequestMatcher.withDefaults().matcher("/post-form.html"),
            PathPatternRequestMatcher.withDefaults().matcher("/post-edit-form.html"),
            PathPatternRequestMatcher.withDefaults().matcher("/error-page.html"),
            
            // 정적 리소스 (모든 HTTP 메서드에 대해 허용)
            PathPatternRequestMatcher.withDefaults().matcher("/css/**"),
            PathPatternRequestMatcher.withDefaults().matcher("/icons/**"),
            PathPatternRequestMatcher.withDefaults().matcher("/components/**"),
            PathPatternRequestMatcher.withDefaults().matcher("/favicon.ico"),
            PathPatternRequestMatcher.withDefaults().matcher("/.well-known/**")
	);

	private void sendErrorResponse(HttpServletResponse response, HttpStatus status, String message, ErrorCode errorCode)
			throws IOException {
		response.setStatus(status.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setCharacterEncoding("UTF-8");

		String errorResponse = String.format("{\"message\": \"%s\", \"errorCode\": \"%s\"}", message, errorCode);
		response.getWriter().write(errorResponse);
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		return EXCLUDE_REQUEST_MATCHERS.stream().anyMatch(matcher -> matcher.matches(request));
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		log.info("요청 URI: " + request.getRequestURI());

		// Authorization 헤더 검증
		String authorizationHeaderValue = request.getHeader(HttpHeaders.AUTHORIZATION);
		
		System.out.println(authorizationHeaderValue);

		if (authorizationHeaderValue == null || !authorizationHeaderValue.startsWith("Bearer ")) {
			sendErrorResponse(response, HttpStatus.UNAUTHORIZED, "Authorization 헤더에 토큰 정보가 포함되어 있지 않습니다.",
					ErrorCode.INVALID_AUTHORIZATION_HEADER);
			return;
		}

		// ATK 추출
		String atk = jwtProvider.substringToken(authorizationHeaderValue);

		// 유저 정보 획득
		try {
			Claims claims = jwtProvider.getUserInfoFromToken(atk);

			UUID userId = UUID.fromString(claims.getSubject());
			String name = claims.get("name", String.class);
			List<String> roles = (List<String>) claims.get("roles");

			CurrentUser currentUser = new CurrentUser(userId, name, roles);

			// 권한 목록 설정
			List<SimpleGrantedAuthority> authorities = roles.stream().map(SimpleGrantedAuthority::new)
					.collect(Collectors.toList());

			// Authentication 생성
			Authentication authentication = new UsernamePasswordAuthenticationToken(currentUser, null, authorities);

			SecurityContextHolder.getContext().setAuthentication(authentication);

			filterChain.doFilter(request, response);
		} catch (ExpiredJwtException e) {
			sendErrorResponse(response, HttpStatus.UNAUTHORIZED, "Access Token이 만료되었습니다. 다시 로그인해주세요.",
					ErrorCode.EXPIRED_ATK);
			return;
		} catch (Exception e) {
			sendErrorResponse(response, HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰입니다.", ErrorCode.INVALID_TOKEN);
			return;
		}

	}

}
