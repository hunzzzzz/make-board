package com.example.board.post.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.board.post.dto.response.PostDetailResponse;
import com.example.board.post.service.PostGetService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PostGetController {
	private final PostGetService postGetService;
	private static final String POST_VIEW_COOKIE_NAME_PATTERN = "post-%s-view";

	private void addCookie(long postId, HttpServletResponse response) {
		Cookie cookie = new Cookie(POST_VIEW_COOKIE_NAME_PATTERN.formatted(postId), "-");

		cookie.setPath("/");
		cookie.setMaxAge(24 * 60 * 60); // 24시간

		response.addCookie(cookie);
	}

	private boolean hasCookieOfPostView(long postId, HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();

		if (cookies != null)
			for (Cookie cookie : cookies)
				if (cookie.getName().equals(POST_VIEW_COOKIE_NAME_PATTERN.formatted(postId)))
					return true;

		return false;
	}

	@GetMapping("/api/posts/{postId}")
	ResponseEntity<PostDetailResponse> get(@PathVariable long postId, HttpServletRequest request, HttpServletResponse response) {
		// 쿠키 존재 여부 확인
		boolean hasCookieOfPostView = hasCookieOfPostView(postId, request);
		// 쿠키가 없다면 추가
		if (!hasCookieOfPostView)
			addCookie(postId, response);

		PostDetailResponse post = postGetService.get(postId, !hasCookieOfPostView);
		
		return ResponseEntity.ok().body(post);
	}
}
