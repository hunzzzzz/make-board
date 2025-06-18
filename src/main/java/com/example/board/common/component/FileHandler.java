package com.example.board.common.component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.example.board.common.exception.file.InvalidFileException;

@Component
public class FileHandler {
	private static final String FILE_NAME_PATTERN = "%s-%s-%s.%s";

	public String getExtension(String fileName) {
		int indexOfComma = fileName.lastIndexOf('.');

		return fileName.substring(indexOfComma + 1);
	}
	
	public boolean hasFiles(List<MultipartFile> files) {
		if (files == null) return false;
		
		List<MultipartFile> actualFiles = files.stream().filter(file -> !file.isEmpty()).collect(Collectors.toList());
		
		return actualFiles.size() > 0;
	}


	public void validateFileExtension(MultipartFile file) {
		if (!file.getOriginalFilename().contains(".") )
			throw new InvalidFileException("유효하지 않은 파일입니다.");
	}

	public String createNewFileName(
			String identifier, 
			long primaryKey, 
			UUID fileId, 
			MultipartFile multipartFile
	) {
		return FILE_NAME_PATTERN.formatted(
				identifier, 
				primaryKey,
				fileId,
				getExtension(multipartFile.getOriginalFilename()))
		;
	}
}
