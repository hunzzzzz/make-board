package com.example.board.comment.controller;

import java.util.Collections;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.board.comment.dto.request.CommentAddRequest;
import com.example.board.comment.service.CommentAddService;
import com.example.board.common.auth.CurrentUser;
import com.example.board.common.auth.UserPrincipal;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CommentAddController {
	private final CommentAddService commentAddService;
	
	@PostMapping("/api/posts/{postId}/comments")
	ResponseEntity<Object> add(
			@PathVariable long postId, 
			@RequestBody CommentAddRequest request, 
			@UserPrincipal CurrentUser currentUser
	) {
		commentAddService.add(postId, request, currentUser.getUserId(), currentUser.getName());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(Collections.EMPTY_MAP);
		
	}

}
