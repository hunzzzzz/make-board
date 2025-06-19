package com.example.board.like.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.board.like.mapper.LikeMapper;
import com.example.board.post.mapper.PostMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LikeService {
	private final LikeMapper likeMapper;
	private final PostMapper postMapper;

	public boolean hasLike(long postId, UUID userId) {
		return likeMapper.hasLike(postId, userId) == 1;
	}

	@Transactional
	public void like(long postId, UUID userId) {
		likeMapper.like(postId, userId);

		postMapper.incrementLikeCount(postId);
	}

	@Transactional
	public void cancelLike(long postId, UUID userId) {
		likeMapper.cancelLike(postId, userId);

		postMapper.decrementLikeCount(postId);
	}
}
