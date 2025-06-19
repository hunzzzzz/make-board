package com.example.board.user.controller;

import java.util.Collections;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.board.user.dto.request.CheckEmailRequest;
import com.example.board.user.dto.request.SignupRequest;
import com.example.board.user.service.SignupService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class SignupController {
	private final SignupService signupService;

	@PostMapping("/api/signup")
	ResponseEntity<UUID> signup(@Valid @RequestBody SignupRequest request) {
		UUID userId = signupService.signup(request);

		return ResponseEntity.status(HttpStatus.CREATED).body(userId);
	}

	@PostMapping("/api/signup/email/check")
	ResponseEntity<Object> checkEmail(@Valid @RequestBody CheckEmailRequest request) {
		signupService.checkEmailDuplication(request.getEmail());
		
		return ResponseEntity.ok().body(Collections.EMPTY_MAP);
	}
}
