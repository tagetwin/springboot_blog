package com.yndg.blog.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import com.yndg.blog.aop.SessionInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer{

	@Value("${file.path}")
	private String fileRealPath;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		WebMvcConfigurer.super.addResourceHandlers(registry);
		
		// 파일 경로 인식하게 하기
		registry.addResourceHandler("/media/**")
		.addResourceLocations("file:///"+fileRealPath)
		.setCachePeriod(3600)
		.resourceChain(true)
		.addResolver(new PathResourceResolver());
		
	}
	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new SessionInterceptor()) // 2. 이( )클래스를 실행시키겠다.
			 .addPathPatterns("/user/profile/**") // 1. 이 주소를 들어가면
			 .addPathPatterns("/post/**")
			 .addPathPatterns("/post/update/**")
			 .addPathPatterns("/post/delete/**");
		
		// addExcludePatterns() 제외시킬떄 사용
	}
}
