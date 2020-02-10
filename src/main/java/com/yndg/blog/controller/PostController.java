package com.yndg.blog.controller;

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

import com.yndg.blog.model.RespCM;
import com.yndg.blog.model.comment.Comment;
import com.yndg.blog.model.post.Post;
import com.yndg.blog.model.post.dto.ReqUpdateDto;
import com.yndg.blog.model.post.dto.ReqWriteDto;
import com.yndg.blog.model.post.dto.RespListDto;
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
		
		List<RespListDto> list= postService.글목록();
		model.addAttribute("list", list);
		
		return "/post/list"; 
	}
	
	@GetMapping("/post/detail/{id}")
	public String detail(@PathVariable int id, Model model) {
		
		Post post = postService.상세보기(id);
		List<Comment> comments = postService.댓글불러오기(id);
		
		model.addAttribute("post", post);
		model.addAttribute("comments", comments);
		
		return "/post/detail";
	}
	
	@GetMapping("/post/write")
	public String write() {
		
		return "/post/write";
	}
	
	
	@PostMapping("/post/write") 	// 인증체크 - 로그인 한사람만 글 작성 가능
	public ResponseEntity<?> write(@RequestBody ReqWriteDto reqWriteDto) {
		
		User principal = (User) session.getAttribute("principal");
		reqWriteDto.setUserId(principal.getId());
		
		int result = postService.작성(reqWriteDto);
		
		if(result == 1) {
			return new ResponseEntity<RespCM>(new RespCM(200, "ok"), HttpStatus.OK);
		}else {
			return new ResponseEntity<RespCM>(new RespCM(500, "fail"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/post/update/{id}") 	// 인증체크 + 권한 - 로그인 한사람+해당 글을 쓴 사람만 수정 가능
	public String update(@PathVariable int id, Model model) {
		
			Post post = postService.수정페이지(id);
			
			if(post != null) {
				model.addAttribute("post", post);
				return "post/update";
			}
			return "/";
			
	}		
//		Post post = postService.수정페이지(id);
//		model.addAttribute("post", post);
//		
//		return "/post/detail";
		
	
	@DeleteMapping("/post/delete/{id}") 	// 인증체크 - 로그인 한사람만 글 삭제 가능 + 해당글은 쓴 사람만 삭제 가능
	public ResponseEntity<?> delete(@PathVariable int id) {
		
		//동일인 체크 session의 principal.id == 해당 post.id
		
		int result = postService.삭제(id);
		System.out.println("삭제:"+result);
		if(result == 1) {
			return new ResponseEntity<RespCM>(new RespCM(200, "ok"), HttpStatus.OK);
		}else {
			return new ResponseEntity<RespCM>(new RespCM(500, "fail"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/post/update/") 	// 인증체크 - 로그인 한사람만 글 수정 가능 + 해당글은 쓴 사람만 삭제 가능
	public ResponseEntity<?> update(@RequestBody ReqUpdateDto reqUpdateDto)  {
		
			
		int result = postService.수정(reqUpdateDto);
					
		if(result == 1) {
			return new ResponseEntity<RespCM>(new RespCM(200, "ok"), HttpStatus.OK);
		}else {
			return new ResponseEntity<RespCM>(new RespCM(500, "fail"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
