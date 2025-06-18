package com.example.board.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// 게시글 목록
		registry.addViewController("/posts").setViewName("posts.html");
		registry.addViewController("/").setViewName("posts.html");
		// 게시글 등록 폼
		registry.addViewController("/posts/add").setViewName("forward:/post-form.html");
		// 게시글 수정 폼
		registry.addViewController("/posts/{postId:\\d+}/edit").setViewName("forward:/post-edit-form.html");
		// 게시글 단건 조회
		registry.addViewController("/posts/{postId:\\d+}").setViewName("forward:/post.html");
	}
}
