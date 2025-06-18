package com.example.board.post.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.board.common.component.TimeFormatter;
import com.example.board.common.exception.post.PostAccessException;
import com.example.board.common.exception.post.PostNotFoundException;
import com.example.board.post.dto.response.PostDetailResponse;
import com.example.board.post.entity.PostStatus;
import com.example.board.post.mapper.PostMapper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PostGetService {
	private PostMapper postMapper;
	private TimeFormatter timeFormatter;

	private void validate(PostDetailResponse post) {
		if (post == null)
			throw new PostNotFoundException("존재하지 않는 게시글입니다.");

		if (post.getStatus().equals(PostStatus.DELETED))
			throw new PostAccessException("접근이 불가능합니다.");
	}

	private void formatDateTime(PostDetailResponse post) {
		post.setFormattedCreatedAt(timeFormatter.formatTimeIntoYyyyMmDd(post.getCreatedAt()));

		if (post.getIsUpdated() != null)
			post.setFormattedUpdatedAt(timeFormatter.formatTimeIntoYyyyMmDd(post.getUpdatedAt()));
	}

	@Transactional
	public PostDetailResponse get(long postId, boolean shouldIncrementPostView) {
		// 게시글 조회
		PostDetailResponse post = postMapper.get(postId);

		// 검증
		validate(post);

		// 작성일 및 수정일 포매팅
		formatDateTime(post);

		// 조회수 증가
		if (shouldIncrementPostView)
			postMapper.incrementViewCount(postId);

		return post;
	}
	
}
