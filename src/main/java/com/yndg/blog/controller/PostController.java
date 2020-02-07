package com.yndg.blog.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.yndg.blog.model.RespCM;
import com.yndg.blog.model.VM.ListVM;
import com.yndg.blog.model.post.Post;
import com.yndg.blog.model.post.dto.ReqDeleteDto;
import com.yndg.blog.model.post.dto.ReqUpdateDto;
import com.yndg.blog.model.post.dto.ReqWriteDto;
import com.yndg.blog.model.user.User;
import com.yndg.blog.service.PostService;

// 안녕 시큐리티 구현 완료


@Controller
public class PostController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private PostService postService;
	
	
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
	
	@GetMapping("/post/write")
	public String write() {
		
		return "/post/write";
	}
	
	
	@PostMapping("/post/write") 	// 인증체크 - 로그인 한사람만 글 작성 가능
	public ResponseEntity<?> write(@RequestBody ReqWriteDto reqWriteDto) {
		
		int result = postService.작성(reqWriteDto);
		
		if(result == 1) {
			return new ResponseEntity<RespCM>(new RespCM(200, "ok"), HttpStatus.OK);
		}else {
			return new ResponseEntity<RespCM>(new RespCM(500, "fail"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/post/update") 	// 인증체크 + 권한 - 로그인 한사람+해당 글을 쓴 사람만 수정 가능
	public String update(@RequestParam int userId, int id, Model model) {
		User principal = (User) session.getAttribute("principal");
		if( principal.getId() == userId) {
			Post post = postService.수정페이지(id);
			model.addAttribute("post", post);
			return "/post/update";
		}else {
			return "/post";
		}
	}
	
	@DeleteMapping("/post/delete/") 	// 인증체크 - 로그인 한사람만 글 삭제 가능 + 해당글은 쓴 사람만 삭제 가능
	public ResponseEntity<?> delete(@RequestBody ReqDeleteDto reqDeleteDto) throws IOException {
		
		User principal = (User) session.getAttribute("principal");
		System.out.println("id: "+principal.getId()+" userId :"+reqDeleteDto.getUserId());
		if(principal.getId() == reqDeleteDto.getUserId()) {
			
			int result = postService.삭제(reqDeleteDto.getId());
			System.out.println("delete :"+result);
			if(result == 1) {
				return new ResponseEntity<RespCM>(new RespCM(200, "ok"), HttpStatus.OK);
			}else {
				return new ResponseEntity<RespCM>(new RespCM(500, "fail"), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}else {
			return new ResponseEntity<RespCM>(new RespCM(500, "fail"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/post/update/") 	// 인증체크 - 로그인 한사람만 글 수정 가능 + 해당글은 쓴 사람만 삭제 가능
	public ResponseEntity<?> update(@RequestBody ReqUpdateDto reqUpdateDto)  {
		
		User principal = (User) session.getAttribute("principal");
		if(principal.getId() == reqUpdateDto.getUserId()) {
			
			int result = postService.수정(reqUpdateDto);
					
			if(result == 1) {
				return new ResponseEntity<RespCM>(new RespCM(200, "ok"), HttpStatus.OK);
			}else {
				return new ResponseEntity<RespCM>(new RespCM(500, "fail"), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}else {
			return new ResponseEntity<RespCM>(new RespCM(500, "fail"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
