package com.example.board.post.controller;

import java.util.Collections;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.board.post.dto.request.PostUpdateRequest;
import com.example.board.post.dto.response.PostUpdateResponse;
import com.example.board.post.service.PostUpdateService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PostUpdateController {
	private final PostUpdateService postUpdateService;
	
	@PutMapping("/api/posts/{postId}")
	ResponseEntity<Object> update(
			@PathVariable long postId,
			@Valid @ModelAttribute PostUpdateRequest request
	) {		
		postUpdateService.update(postId, request);
		
		return ResponseEntity.ok(Collections.EMPTY_MAP);
	}
	
	@GetMapping("/api/posts/{postId}/edit")
	ResponseEntity<PostUpdateResponse> getFormData(@PathVariable long postId) {
		PostUpdateResponse post = postUpdateService.getFormData(postId);
		
		return ResponseEntity.ok(post);
	}
}
