package com.example.board.post.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.board.common.exception.ErrorCode;
import com.example.board.common.exception.post.PostAccessException;
import com.example.board.post.mapper.PostMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostDeleteService {
	private final PostMapper postMapper;
	private static final String DELETED_POST_TITLE = "삭제된 게시글입니다.";

	@Transactional
	public void delete(long postId, UUID userId) {
		UUID authorId = postMapper.getAuthorId(postId);
		
		if (!authorId.equals(userId)) {
			throw new PostAccessException("다른 유저의 게시글을 삭제할 수 없습니다.", ErrorCode.CANNOT_DELETE_OTHERS_POST);
		}
		
		postMapper.delete(postId, DELETED_POST_TITLE);
	}

}
