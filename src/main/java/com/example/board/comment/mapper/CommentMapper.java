package com.example.board.comment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.board.comment.dto.response.CommentResponse;
import com.example.board.comment.entity.Comment;

@Mapper
public interface CommentMapper {
	/*
	 * 댓글 등록 시
	 */
	void add(Comment comment);
	
	/*
	 * 댓글 목록 조회 시
	 */
	List<CommentResponse> getAll(long postId, long cursor, int pageSize);

}
