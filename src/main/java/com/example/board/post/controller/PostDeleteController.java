package com.example.board.post.controller;

import java.util.Collections;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.board.post.service.PostDeleteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PostDeleteController {
	private final PostDeleteService postDeleteService;

	@DeleteMapping("/api/posts/{postId}")
	ResponseEntity<Object> delete(@PathVariable long postId) {
		postDeleteService.delete(postId);
		
		return ResponseEntity.ok().body(Collections.EMPTY_MAP);
	}

}