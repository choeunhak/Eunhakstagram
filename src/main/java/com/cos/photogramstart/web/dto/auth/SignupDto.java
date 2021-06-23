package com.cos.photogramstart.web.dto.auth;

import com.cos.photogramstart.domain.user.User;

import lombok.Data;

//요청하는 dto requestdto
@Data
public class SignupDto {

	
	private String username;
	private String password;
	private String email;
	private String name;
	
	
	//db에 저장(간단한 방식)
	public User toEntity() {
		return User.builder()
				.username(username)
				.password(password)
				.email(email)
				.name(name)
				.build();
	}
}
