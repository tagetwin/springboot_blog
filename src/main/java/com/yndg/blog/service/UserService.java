package com.yndg.blog.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.yndg.blog.model.ReturnCode;
import com.yndg.blog.model.user.User;
import com.yndg.blog.model.user.dto.ReqJoinDto;
import com.yndg.blog.model.user.dto.ReqLoginDto;
import com.yndg.blog.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private HttpSession session;

	@Value("${file.path}")
	private String fileRealPath; // 서버에 배포하면 경로 변경해야함.
	
	@Transactional
	public int 회원가입(ReqJoinDto dto) {

		try {

			int result = userRepository.findByUsername(dto.getUsername());
						
			if (result == 1) {
				return ReturnCode.아이디중복;
			} else {
				return userRepository.save(dto);
			}
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	
	
	public User 로그인(ReqLoginDto dto) {
		return userRepository.findByUsernameAndPassword(dto);
		
	}
	
	@Transactional
	public int 프로필(int id, String password, MultipartFile profile) {

		UUID uuid = UUID.randomUUID();
		String uuidFilename = uuid + "_" + profile.getOriginalFilename();

		// nio
		Path filePath = Paths.get(fileRealPath+uuidFilename);
		
		System.out.println(uuidFilename);
		try {
			
			int result = userRepository.update(id, password, uuidFilename);
			Files.write(filePath, profile.getBytes());
			User user = userRepository.findById(id);
			session.setAttribute("principal", user);
			
			if (result == 1) {
				return result;
			} else {
				return ReturnCode.오류;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
}
