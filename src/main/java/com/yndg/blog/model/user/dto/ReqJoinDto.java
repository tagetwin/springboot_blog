package com.yndg.blog.model.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class ReqJoinDto {
<<<<<<< HEAD

	@NotBlank(message = "유저네임을 입력하세요.")
	@Size(min = 7, max = 15, message = "유저네임 길이가 잘못 되었습니다.")
	@Pattern(regexp = "^[a-zA-Z0-9]*$", message = "한글입력불가")
	private String username;
	
	@NotBlank(message = "패스워드를 입력하세요.")
	@Size(min = 7, max = 15, message = "패스워드 길이가 잘못 되었습니다.")
	private String password;
	
	@NotBlank(message = "이메일을 입력하세요.")
	@Size(min = 5, max = 30, message = "이메일 길이가 잘못 되었습니다.")
	@Email(message="이메일 양식이 틀렸습니다.")
=======
	
//	@Pattern(regexp = "^[a-zA-Z0-9]*$", message = "유저네임에 한글이 입력될 수 없습니다.")
//	@Size(max = 15, message = "유저네임 길이가 잘못 되었습니다.")
//	@NotBlank(message = "유저네임을 입력하세요.")
	private String username;
	
	
//	@Size(max = 15, message = "패스워드 길이가 잘못 되었습니다.")
//	@NotBlank(message = "패스워드를 입력하세요.")
	private String password;
	
//	@Size(max = 30, message = "이메일 길이가 잘못 되었습니다.")
//	@Email(message="이메일 양식이 틀렸습니다.")
//	@NotBlank(message = "이메일을 입력하세요.")
>>>>>>> 6d33233bbbed3dcd7c7fca03557d54855b92c667
	private String email;
	
	
}
