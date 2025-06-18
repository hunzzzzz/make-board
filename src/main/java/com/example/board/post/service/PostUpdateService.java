package com.example.board.post.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.board.file.service.FileDeleteService;
import com.example.board.file.service.FileSaveService;
import com.example.board.post.dto.request.PostUpdateRequest;
import com.example.board.post.dto.response.PostUpdateResponse;
import com.example.board.post.mapper.PostMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostUpdateService {
	private final FileDeleteService fileDeleteService;
	private final FileSaveService fileSaveService;
	private final PostMapper postMapper;

	@Transactional
	public void update(long postId, PostUpdateRequest request) {
		// 게시글 기본 정보 수정
		postMapper.update(postId, request.toEntity());

		// 새로운 첨부파일 저장
		fileSaveService.save(postId, request.getFiles());

		// 제거된 첨부파일 삭제
		if (request.getDeletedFileIds() != null && request.getDeletedFileIds().size() > 0)
			fileDeleteService.delete(request.getDeletedFileIds());
	}
	
	@Transactional(readOnly = true)
	public PostUpdateResponse getFormData(long postId) {
		PostUpdateResponse post = postMapper.getFormData(postId);
		
		return post;
	}
}
