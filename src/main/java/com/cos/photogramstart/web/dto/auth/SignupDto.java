package com.cos.photogramstart.web.dto.auth;

import lombok.Data;

//요청하는 dto requestdto
@Data
public class SignupDto {

	
	private String username;
	private String password;
	private String email;
	private String name;
}
