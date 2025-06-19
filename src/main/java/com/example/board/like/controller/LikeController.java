package com.example.board.like.controller;

import java.util.Collections;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.board.like.service.LikeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LikeController {
	private final LikeService likeService;
	private final UUID SAMPLE_USER_ID = UUID.fromString("3a941437-08e4-4e95-9a1b-3d45027ac7fd"); // TODO: 추후 수정

	@PostMapping("/api/posts/{postId}/like")
	public ResponseEntity<Object> like(@PathVariable long postId) {
		likeService.like(postId, SAMPLE_USER_ID);

		return ResponseEntity.ok().body(Collections.EMPTY_MAP);
	}

	@DeleteMapping("/api/posts/{postId}/like")
	public ResponseEntity<Object> cancelLike(@PathVariable long postId) {
		likeService.cancelLike(postId, SAMPLE_USER_ID);

		return ResponseEntity.ok().body(Collections.EMPTY_MAP);
	}

	@GetMapping("/api/posts/{postId}/like/check")
	public ResponseEntity<Boolean> checkHasLike(@PathVariable long postId) {
		boolean hasLike = likeService.hasLike(postId, SAMPLE_USER_ID);

		return ResponseEntity.ok().body(hasLike);
	}

}
