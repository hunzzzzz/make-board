package com.example.board.file.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.board.common.component.FileHandler;
import com.example.board.common.exception.file.DirectoryCreationException;
import com.example.board.common.exception.file.FileSystemException;
import com.example.board.file.entity.File;
import com.example.board.file.mapper.FileMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FileSaveService {
	private final FileHandler fileHandler;
	private final FileMapper fileMapper;
	private final String uploadDir;

	public void checkIfDirectoryExists() {
		try {
			Path uploadPath = Paths.get(uploadDir);

			if (!Files.exists(uploadPath)) {
				Files.createDirectories(uploadPath);

				log.info("디렉터리 생성 완료:" + uploadDir);
			}
		} catch (IOException e) {
			log.error("디렉터리 생성 실패: " + uploadDir + "\n" + e.getMessage());

			throw new DirectoryCreationException();
		}
	}

	public FileSaveService(
			FileHandler fileHandler, 
			FileMapper fileMapper,
			@Value("${file.upload.dir}") String uploadDir
	) {
		this.fileHandler = fileHandler;
		this.fileMapper = fileMapper;
		this.uploadDir = uploadDir;
		
		// 디렉터리 존재여부 확인
		checkIfDirectoryExists();
	}

	private void saveFile(long postId, MultipartFile multipartFile) {
		try {
			// 검증
			fileHandler.validateFileExtension(multipartFile);

			// 파일명 생성
			UUID fileId = UUID.randomUUID();
			String savedFileName = fileHandler.createNewFileName("posts", postId, fileId, multipartFile);

			// 저장
			saveFileInPath(multipartFile, savedFileName);
			saveFileInDb(postId, fileId, multipartFile, savedFileName);
		} catch (IOException e) {
			log.error("파일 저장 실패: id:" + postId + "\n" + e.getMessage());

			throw new FileSystemException();
		}

	}

	private void saveFileInPath(MultipartFile multipartFile, String savedFileName) throws IOException {
		Path filePath = Paths.get(uploadDir, savedFileName);

		Files.copy(
				multipartFile.getInputStream(), 
				filePath
		);
	}

	private void saveFileInDb(long postId, UUID fileId, MultipartFile multipartFile, String savedFileName) {
		File file = File.builder()
				.fileId(fileId)
				.postId(postId)
				.originalFileName(multipartFile.getOriginalFilename())
				.savedFileName(savedFileName)
				.path(uploadDir)
				.fileSize(multipartFile.getSize())
				.fileType(multipartFile.getContentType())
				.build();

		fileMapper.save(file);
	}

	public void save(long postId, List<MultipartFile> files) {
		// 첨부파일이 존재하지 않는 경우 해당 메서드 종료
		if (!fileHandler.hasFiles(files)) return;
		
		// 파일 저장
		files.forEach((file) -> saveFile(postId, file));
	}
}
