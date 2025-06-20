package com.example.board.post.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.board.common.auth.CurrentUser;
import com.example.board.common.auth.UserPrincipal;
import com.example.board.post.service.PostCheckAuthorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PostCheckAuthorController {
	private final PostCheckAuthorService postCheckAuthorService;
	
	@GetMapping("/api/posts/{postId}/author/check")
	public ResponseEntity<Boolean> checkAuthor(@PathVariable long postId, @UserPrincipal CurrentUser currentUser) {
		boolean result = postCheckAuthorService.checkAuthor(postId, currentUser.getUserId());
		
		return ResponseEntity.ok().body(result);
	}
}
