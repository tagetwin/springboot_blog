package com.yndg.blog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
		
		if(session.getAttribute("principal") != null) {
			return "/post/write";
		}else {
			return "redirect:/user/login";
		}
		
	}
	
	@GetMapping("/post/update/{id}") 	// 인증체크 + 권한 - 로그인 한사람+해당 글을 쓴 사람만 수정 가능
	public String update() {
		
		return "/post/update";
	}
	
	
}
