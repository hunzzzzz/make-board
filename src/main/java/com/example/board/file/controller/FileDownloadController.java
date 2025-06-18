package com.example.board.file.controller;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.board.file.dto.response.FileDownloadResponse;
import com.example.board.file.service.FileDownloadService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class FileDownloadController {
	private final FileDownloadService fileDownloadService;

	@GetMapping("/api/files/{fileId}")
	ResponseEntity<Resource> download(@PathVariable UUID fileId) {
		FileDownloadResponse file = fileDownloadService.download(fileId);
		
		String encodedFileName = new String(
				file.getOriginalFileName().getBytes(StandardCharsets.UTF_8),
				StandardCharsets.ISO_8859_1
		);

		return ResponseEntity
				.ok()
				.contentType(MediaType.parseMediaType(file.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + encodedFileName + "\"")
				.body(file.getResource());
	}
}
