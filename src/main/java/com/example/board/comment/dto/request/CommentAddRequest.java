package com.example.board.comment.dto.request;

import java.util.UUID;

import com.example.board.comment.entity.Comment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentAddRequest {
	private String content;
	private Long parentId;
	
	public Comment toEntity(long postId, UUID userId, String author) {
		return new Comment(postId, userId, parentId, content, author);
	}

}
