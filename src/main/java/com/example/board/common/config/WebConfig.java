package com.example.board.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// 게시글 목록
		registry.addViewController("/posts").setViewName("forward:posts.html");
		registry.addViewController("/").setViewName("forward:posts.html");
		// 게시글 등록 폼
		registry.addViewController("/posts/add").setViewName("forward:/post-form.html");
	}
}
