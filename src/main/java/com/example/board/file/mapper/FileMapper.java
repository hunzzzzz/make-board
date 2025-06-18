package com.example.board.file.mapper;

import java.util.List;
import java.util.UUID;

import org.apache.ibatis.annotations.Mapper;

import com.example.board.file.dto.response.FileDownloadResponse;
import com.example.board.file.entity.File;

@Mapper
public interface FileMapper {
	// 파일 저장
	void save(File file);
	
	// 파일 다운로드
	FileDownloadResponse get(UUID fileId);
	
	// 파일 삭제
	void deleteAll(List<UUID> deletedFileIds);
	List<String> getSavedFileNames(List<UUID> deletedFileIds);

}
