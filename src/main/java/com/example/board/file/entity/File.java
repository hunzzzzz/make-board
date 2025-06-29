package com.example.board.file.entity;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class File {
	private UUID fileId;
	private long postId;
	private String originalFileName;
	private String savedFileName;
	private String path;
	private long fileSize;
	private String fileType;

	public File(UUID fileId, long postId, String originalFileName, String savedFileName, String path, long fileSize, String fileType) {
		this.fileId = fileId;
		this.postId = postId;
		this.originalFileName = originalFileName;
		this.savedFileName = savedFileName;
		this.path = path;
		this.fileSize = fileSize;
		this.fileType = fileType;
	}
}
