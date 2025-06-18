package com.example.board.post.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.board.file.service.FileSaveService;
import com.example.board.post.dto.request.PostAddRequest;
import com.example.board.post.entity.Post;
import com.example.board.post.mapper.PostMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostAddService {
	private final FileSaveService fileSaveService;
	private final PostMapper postMapper;

	@Transactional
	public void add(PostAddRequest request, UUID userId, String author) {
		System.out.println(request.getFiles() == null);
		
		// 저장
		Post post = request.toEntity(userId, author);
		postMapper.add(post);

		// 파일 저장
		fileSaveService.save(post.getPostId(), request.getFiles());
	}
}
