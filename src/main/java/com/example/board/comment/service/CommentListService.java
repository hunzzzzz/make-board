package com.example.board.comment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.board.comment.dto.response.CommentResponse;
import com.example.board.comment.mapper.CommentMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentListService {
	private final CommentMapper commentMapper;
	
	List<CommentResponse> getAll(long postId, long cursor) {
		return commentMapper.getAll(postId, cursor, 10);
	}
}
