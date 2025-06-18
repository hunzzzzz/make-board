package com.example.board.post.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.board.post.mapper.PostMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostDeleteService {
	private final PostMapper postMapper;
	private static final String DELETED_POST_TITLE = "삭제된 게시글입니다.";

	@Transactional
	public void delete(long postId) {
		postMapper.delete(postId, DELETED_POST_TITLE);
	}

}
