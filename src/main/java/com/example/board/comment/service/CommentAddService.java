package com.example.board.comment.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.board.comment.dto.request.CommentAddRequest;
import com.example.board.comment.entity.Comment;
import com.example.board.comment.mapper.CommentMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentAddService {
	private final CommentMapper commentMapper;
	
	public void add(long postId, CommentAddRequest request, UUID userId, String author) {
		Comment comment = request.toEntity(postId, userId, author);
		
		commentMapper.add(comment);
	}
}
