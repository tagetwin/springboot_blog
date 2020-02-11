package com.yndg.blog.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.yndg.blog.model.ReturnCode;
import com.yndg.blog.model.user.User;
import com.yndg.blog.model.user.dto.ReqJoinDto;
import com.yndg.blog.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Value("${file.path}")
	private String fileRealPath; // 서버에 배포하면 경로 변경해야함.
	
	@Autowired
	private BCryptPasswordEncoder passwordEncode; 
	
	@Transactional
	public int 회원가입(ReqJoinDto dto) {

		try {

			int result = userRepository.findByUsername(dto.getUsername());
						
			if (result == 1) {
				return ReturnCode.아이디중복;
			} else {
				// password 암호화 하기
				String encodePassword = passwordEncode.encode(dto.getPassword());
				dto.setPassword(encodePassword);
				return userRepository.save(dto);
			}
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	
	
//	public User 로그인(ReqLoginDto dto) {
//		return userRepository.findByUsernameAndPassword(dto);
//		
//	}
	
	@Transactional
	public int 프로필(int id, String password, MultipartFile profile, User principal) {

		UUID uuid = UUID.randomUUID();
		String uuidFilename = uuid + "_" + profile.getOriginalFilename();

		// nio
		Path filePath = Paths.get(fileRealPath+uuidFilename);
		
		System.out.println(uuidFilename);
		try {
			
			String encodePassword = passwordEncode.encode(password);
			int result = userRepository.update(id, encodePassword, uuidFilename);
			
//			int result = userRepository.update(id, password, uuidFilename);
			Files.write(filePath, profile.getBytes());
			User user = userRepository.findById(id);
//			session.setAttribute("principal", user);
			principal.setPassword(user.getPassword());
			principal.setEmail(user.getEmail());
			principal.setProfile(user.getProfile());
			
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
