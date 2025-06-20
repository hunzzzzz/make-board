package com.example.board.comment.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentResponse {
	private long commentId;
	
	private UUID userId;
	
	private String author;

	private String content;
	
	private LocalDateTime createdAt;
}
