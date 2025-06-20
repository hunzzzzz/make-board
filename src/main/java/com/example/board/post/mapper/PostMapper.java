package com.example.board.post.mapper;

import java.util.List;
import java.util.UUID;

import org.apache.ibatis.annotations.Mapper;

import com.example.board.post.dto.response.PostDetailResponse;
import com.example.board.post.dto.response.PostResponse;
import com.example.board.post.dto.response.PostUpdateResponse;
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
	 * 게시글 단건 조회에 필요한 메서드
	 */
	PostDetailResponse get(long postId);
	
	void incrementViewCount(long postId);
	
	/*
	 * 게시글 등록에 필요한 메서드
	 */
	void add(Post post);
	
	/*
	 * 게시글 수정에 필요한 메서드
	 */
	PostUpdateResponse getFormData(long postId);
	void update(long postId, Post post);
	
	/*
	 * 게시글 삭제에 필요한 메서드
	 */
	void delete(long postId, String deletedPostTitle);
	UUID getAuthorId(long postId);
	
	/*
	 * 게시글 좋아요에 필요한 메서드
	 */
	void incrementLikeCount(long postId);
	void decrementLikeCount(long postId);
}
