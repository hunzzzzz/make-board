package com.example.board.common.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.board.common.auth.UserPrincipalArgumentResolver;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
	private final UserPrincipalArgumentResolver userPrincipalArgumentResolver;
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// 회원가입
		registry.addViewController("/signup").setViewName("signup.html");
		// 로그인
		registry.addViewController("/login").setViewName("login.html");
		// 게시글 목록
		registry.addViewController("/posts").setViewName("posts.html");
		registry.addViewController("/").setViewName("posts.html");
		// 게시글 등록 폼
		registry.addViewController("/posts/add").setViewName("forward:/post-form.html");
		// 게시글 수정 폼
		registry.addViewController("/posts/{postId:\\d+}/edit").setViewName("forward:/post-edit-form.html");
		// 게시글 단건 조회
		registry.addViewController("/posts/{postId:\\d+}").setViewName("forward:/post.html");
		// 에러 페이지
		registry.addViewController("/error-page").setViewName("error-page.html");
	}
	
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(userPrincipalArgumentResolver);
	}
}
