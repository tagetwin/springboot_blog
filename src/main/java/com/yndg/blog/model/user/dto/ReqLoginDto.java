package com.yndg.blog.model.user.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class ReqLoginDto {
	
//	@Pattern(regexp = "^[a-zA-Z0-9]*$", message = "유저네임에 한글이 입력될 수 없습니다.")
//	@Size(max = 15, message = "유저네임 길이가 잘못 되었습니다.")
	@NotBlank(message = "유저네임을 입력하세요.")
	@NotEmpty
	private String username;
	
	
//	@Size(max = 15, message = "패스워드 길이가 잘못 되었습니다.")
	@NotBlank(message = "패스워드를 입력하세요.")
	private String password;
	
	
}
