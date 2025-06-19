package com.example.board.post.controller;

import java.util.Collections;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.board.common.auth.CurrentUser;
import com.example.board.common.auth.UserPrincipal;
import com.example.board.post.dto.request.PostAddRequest;
import com.example.board.post.service.PostAddService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PostAddController {
	private final PostAddService postAddService;

	@PostMapping("/api/posts/add")
	ResponseEntity<Object> add(
			@UserPrincipal CurrentUser currentUser,
			@Valid @ModelAttribute PostAddRequest request
	) {
		postAddService.add(request, currentUser.getUserId(), currentUser.getName());
			
		return ResponseEntity.status(HttpStatus.CREATED).body(Collections.EMPTY_MAP);
	}
}
