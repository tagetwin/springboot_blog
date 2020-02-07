package com.yndg.blog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.yndg.blog.model.user.User;

// 안녕 시큐리티 구현 완료


@Controller
public class PostController {
	
	@Autowired
	HttpSession session;
	
	@GetMapping({"", "/", "/post"})
	public String posts() {
		return "/post/list"; 
	}
	
	@GetMapping("/post/{id}")
	public String post() {
		
		return "/post/detail";
	}

	@GetMapping("/post/write") 	// 인증체크 - 로그인 한사람만 글 작성 가능
	public String write() {
		
		return "/post/write";
		
	}
	
	@GetMapping("/post/update/{postId}") 	// 인증체크 + 권한 - 로그인 한사람+해당 글을 쓴 사람만 수정 가능
	public String update(@PathVariable int postId, @RequestParam int userId) {
		
		User principal = (User) session.getAttribute("principal");
		
		if( principal.getId() == userId) {
			return "/post/update";
		}else {
			return "/post/login";
		}
	}
	
}
