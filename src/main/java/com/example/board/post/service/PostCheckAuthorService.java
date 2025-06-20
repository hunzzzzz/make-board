package com.example.board.post.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.board.post.mapper.PostMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostCheckAuthorService {
	private final PostMapper postMapper;

	@Transactional(readOnly = true)
	public boolean checkAuthor(long postId, UUID userId) {
		UUID authorId = postMapper.getAuthorId(postId);
		
		return userId.equals(authorId);
	}
}
