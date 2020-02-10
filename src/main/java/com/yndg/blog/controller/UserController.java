package com.yndg.blog.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yndg.blog.model.RespCM;
import com.yndg.blog.model.ReturnCode;
import com.yndg.blog.model.user.User;
import com.yndg.blog.model.user.dto.ReqJoinDto;
import com.yndg.blog.model.user.dto.ReqLoginDto;
import com.yndg.blog.service.UserService;
import com.yndg.blog.util.Script;

@Controller
public class UserController {
	
//	private static final String TAG ="UserController"; 
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private HttpSession session;
	
	
	@GetMapping("/user/join")
	public String join() {
		return "/user/join";
	}
	
	@GetMapping("/user/login")
	public String login() {
		
		return "/user/login";
	}
	
	@GetMapping("/user/logout")
	public String logout() {
		
		session.invalidate();
		
		return "redirect:/";
	}
	
	// 인증, 동일인 체크(권한?) 스크립트가 안된다?
	@GetMapping("/user/profile/{id}")
	public String profile(@PathVariable int id) {
		
		User principal = (User) session.getAttribute("principal");
		
		if(principal.getId() == id) {
			return "/user/profile";
		}else {
			// 인증이 되지 않은 사용자 입니다.
			return "user/login";
		}
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
			return new ResponseEntity<RespCM>(new RespCM(ReturnCode.아이디중복, "아이디중복"), HttpStatus.OK);
		}else if(result == 1) {
			return new ResponseEntity<RespCM>(new RespCM(200, "ok"), HttpStatus.OK);
		}else {
			return new ResponseEntity<RespCM>(new RespCM(500, "fail"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PostMapping("/user/login") // 컨트롤러의 매개변수는 autowired 톰캣의 객체는 springcontext에서 들고 있다.
	public ResponseEntity<?> login(@Valid @RequestBody ReqLoginDto dto, BindingResult bindingResult) {

		// request 검증 = AOP 로 처리할 예정
		// 서비스 호출
		User principal = userService.로그인(dto);
		if(principal != null) {
			session.setAttribute("principal", principal);
			return new ResponseEntity<RespCM>(new RespCM(200, "ok"), HttpStatus.OK);
		}else {
			return new ResponseEntity<RespCM>(new RespCM(400, "fail"), HttpStatus.BAD_REQUEST);
			
		}
	}
	
	// form:form 사용함!!
	@PutMapping("/user/profile/")
	public @ResponseBody String profile(@RequestParam int id, @RequestParam String password, @RequestParam MultipartFile profile) {
		
		int result = userService.프로필(id, password, profile);
		System.out.println("result"+result);
		
		if(result == 1) {
			return Script.href("수정완료", "/");
		}else {
			return Script.back("수정실패");
		}
	}
}


