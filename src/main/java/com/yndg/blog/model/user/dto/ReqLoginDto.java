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
	
	@NotBlank(message = "유저네임을 입력하세요.")
	@NotEmpty
	private String username;
	
	@NotBlank(message = "패스워드를 입력하세요.")
	private String password;
	
	
}
