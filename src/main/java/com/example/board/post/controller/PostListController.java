package com.example.board.post.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.board.post.dto.response.PostPageResponse;
import com.example.board.post.dto.response.PostResponse;
import com.example.board.post.entity.SortCondition;
import com.example.board.post.service.PostListService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PostListController {
	private final PostListService postListService;
	
	@GetMapping("/api/posts")
	ResponseEntity<Map<String,Object>> getAll(
			@RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false) String keyword,
			@RequestParam(required = false, defaultValue = "LATEST") String sort
	) {
		// 게시글 정보 조회
		List<PostResponse> posts = postListService.getAll(page, keyword, SortCondition.valueOf(sort));
		
		// 페이지 정보 조회
		PostPageResponse pageResponse = postListService.getPageInfo(page, keyword);
		
        // 두 정보를 하나의 Map에 담아 JSON으로 응답
        Map<String, Object> response = new HashMap<>();
        response.put("posts", posts);
        response.put("pageInfo", pageResponse);
        
        return ResponseEntity.ok(response);
	}
}
