package com.yndg.blog.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.yndg.blog.model.VM.ListVM;
import com.yndg.blog.model.post.Post;
import com.yndg.blog.model.user.User;
import com.yndg.blog.service.PostService;

// 안녕 시큐리티 구현 완료


@Controller
public class PostController {
	
	@Autowired
	HttpSession session;
	
	@Autowired
	PostService postService;
	
	@GetMapping({"", "/", "/post"})
	public String posts(Model model) {
		
		List<ListVM> list= postService.글목록();
		model.addAttribute("list", list);
		
		return "/post/list"; 
	}
	
	@GetMapping("/post/detail/{id}")
	public String detail(@PathVariable int id, Model model) {
		
		Post post = postService.상세보기(id);
		model.addAttribute("post", post);
		
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
	
	@DeleteMapping("/post/delete/{id}") 	// 인증체크 - 로그인 한사람만 글 삭제 가능
	public String delete(@PathVariable int id) {
		
		int result = postService.삭제(id);

		return "/post/write";
	}
}
