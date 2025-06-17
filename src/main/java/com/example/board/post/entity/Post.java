package com.example.board.post.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Post {
	private long postId;
	
	private UUID userId;
	
	private PostStatus status;
	
	private String title;
	
	private String content;
	
	private String author;
	
	private int viewCount;
	
	private int likeCount;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;

}
