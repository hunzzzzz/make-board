package com.example.board.post.dto.request;

import java.util.List;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.example.board.post.entity.Post;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostUpdateRequest {
	@Size(max = 255, message = "제목은 255자를 초과할 수 없습니다.")
	private String title;

	private String content;

	private List<MultipartFile> files;

	private List<UUID> deletedFileIds;
	
	public Post toEntity() {
		return new Post(title, content);
	}
}