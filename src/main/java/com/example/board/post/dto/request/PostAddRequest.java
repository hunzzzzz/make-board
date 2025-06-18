package com.example.board.post.dto.request;

import java.util.List;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.example.board.post.entity.Post;

import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PostAddRequest {
	@Size(max = 255, message = "제목은 255자를 초과할 수 없습니다.")
	String title;

	String content;

	private List<MultipartFile> files;

	public Post toEntity(UUID userId, String author) {
		return new Post(userId, title, content, author);
	}
}