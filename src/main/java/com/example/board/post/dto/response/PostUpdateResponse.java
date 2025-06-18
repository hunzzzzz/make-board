package com.example.board.post.dto.response;

import java.util.List;

import com.example.board.file.dto.response.FileResponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostUpdateResponse {
	private long postId;
	private String title;
	private String content;
	private List<FileResponse> files;
}
