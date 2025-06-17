package com.example.board.post.dto.response;

import java.time.LocalDateTime;

import com.example.board.post.entity.PostStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostResponse {
	private long postId;
	
	private PostStatus status;
	
	private String title;
	
	private String author;
	
	private int viewCount;
	
	private int likeCount;
	
	private LocalDateTime createdAt;
	
	private String formattedCreatedAt;
}
