package com.example.board.comment.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.board.comment.dto.response.CommentResponse;
import com.example.board.comment.service.CommentListService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CommentListController {
	private final CommentListService commentListService;
	
	@GetMapping("/api/posts/{postId}/comments")
	ResponseEntity<List<CommentResponse>> getAll(
			@PathVariable Long postId,
			@RequestParam(required = false, defaultValue = "0") Long cursor
	) {
		List<CommentResponse> comments = commentListService.getAll(postId, cursor);
		
		return ResponseEntity.ok(comments);
	}
}
