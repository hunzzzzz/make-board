package com.example.board.post.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.board.post.dto.response.PostResponse;
import com.example.board.post.entity.Post;
import com.example.board.post.entity.SortCondition;


@Mapper
public interface PostMapper {
	/*
	 * 게시글 전체 목록 조회에 필요한 메서드
	 */
	List<PostResponse> getAll(int pageSize, int offset, String keyword, SortCondition sort);
	
	int countAllPosts(String keyword);
	
	/*
	 * 게시글 등록에 필요한 메서드
	 */
	void add(Post post);
}
