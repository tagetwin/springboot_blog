package com.yndg.blog.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.yndg.blog.model.RespCM;
import com.yndg.blog.model.RespCode;
import com.yndg.blog.model.user.dto.ReqJoinDto;
import com.yndg.blog.service.UserService;

@Controller
public class UserController {
	
//	private static final String TAG ="UserController"; 
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/user/join")
	public String join() {
		return "/user/join";
	}
	
	@GetMapping("/user/login")
	public String login() {
		return "/user/login";
	}
	
	@GetMapping("/user/profile/{id}")
	public String profile() {
		return "/user/profile";
	}
	
	// 메시지 컨버터는 request 받을때 setter로 호출한다.
	@PostMapping("/user/join")
	public ResponseEntity<?> join(@Valid @RequestBody ReqJoinDto dto, BindingResult bindingResult) {
	
		// 한글뱉어내기 어노테이션에서 없으면
//		errorMap.put("username", "한글입력불가");
		
		
		if(bindingResult.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
			
			for(FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			
			return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
		}
		
		int result = userService.회원가입(dto);
		
		if(result == -2) {
			return new ResponseEntity<RespCM>(new RespCM(RespCode.아이디중복, "아이디중복"), HttpStatus.OK);
		}else if(result == 1) {
			return new ResponseEntity<RespCM>(new RespCM(200, "ok"), HttpStatus.OK);
		}else {
			return new ResponseEntity<RespCM>(new RespCM(500, "fail"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
