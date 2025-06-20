package com.example.board.comment.controller;

import java.util.Collections;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CommentListController {
	@GetMapping("/api/posts/{postId}/comments")
	ResponseEntity<Object> getAll() {
		return ResponseEntity.ok().body(Collections.EMPTY_MAP); // TODO
	}
}
