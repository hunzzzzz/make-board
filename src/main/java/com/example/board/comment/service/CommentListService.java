package com.example.board.comment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.board.comment.dto.response.CommentResponse;
import com.example.board.comment.mapper.CommentMapper;
import com.example.board.common.component.TimeFormatter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentListService {
	private final int COMMENT_PAGE_SIZE = 5;
	private final CommentMapper commentMapper;
	private final TimeFormatter timeFormatter;
	
	
	public List<CommentResponse> getAll(long postId, long cursor) {
		List<CommentResponse> comments = commentMapper.getAll(postId, cursor, COMMENT_PAGE_SIZE);
		
		comments.stream().forEach((comment) -> {
			timeFormatter.formatTime(comment.getCreatedAt());
		});
		
		return comments;
	}
}
