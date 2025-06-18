package com.example.board.post.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.example.board.file.dto.response.FileResponse;
import com.example.board.post.entity.PostStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDetailResponse {
	private long postId;
	private UUID userId;
	private PostStatus status;
	private String title;
	private String content;
	private String author;
	private int viewCount;
	private int likeCount;
	private LocalDateTime createdAt;
	private String formattedCreatedAt;
	private LocalDateTime updatedAt;
	private Boolean isUpdated;
	private String formattedUpdatedAt;
	private List<FileResponse> files;
	
}
