package com.example.board.post.controller;

import java.util.Collections;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.board.post.dto.request.PostAddRequest;
import com.example.board.post.service.PostAddService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PostAddController {
	private final PostAddService postAddService;

	@PostMapping("/api/posts")
	ResponseEntity<Object> add(
			@Valid @ModelAttribute PostAddRequest request
	) {
		// TODO: 추후 토큰에서 유저 정보를 추출하는 로직으로 구현
		UUID userId = UUID.fromString("3a941437-08e4-4e95-9a1b-3d45027ac7fd");
		String author = "관리자";
		
		postAddService.add(request, userId, author);
			
		return ResponseEntity.status(HttpStatus.CREATED).body(Collections.EMPTY_MAP);
	}
}
