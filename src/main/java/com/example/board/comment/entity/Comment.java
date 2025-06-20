package com.example.board.comment.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Comment {
	private long commentId;
	
	private long postId;
	
	private UUID userId;
	
	private Long parentId;
	
	private String content;
	
	private String author;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;

	public Comment(long postId, UUID userId, Long parentId, String content, String author) {
		this.postId = postId;
		this.userId = userId;
		this.parentId = parentId;
		this.content = content;
		this.author = author;
	}
	
}
