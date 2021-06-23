package com.cos.photogramstart.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cos.photogramstart.web.dto.auth.SignupDto;

@Controller
public class AuthController {

	
	
	private static final Logger log = LoggerFactory.getLogger(AuthController.class);

	@GetMapping("/auth/signin")
	public String signinForm() {
		return "auth/signin";	
	}
	
	@GetMapping("/auth/signup")
	public String signupForm() {
		return "auth/signup";
	}
	
	//회원가입버튼을 누르면 -> auth/signup으로간다->그후에 csrf토큰으로 막힌다
	@PostMapping("/auth/signup")
	public String signup(SignupDto signupDto) {
		//이렇게만 하면 403에러가 뜸
		//security가 csrf로 1차적으로막는거다
		//토큰이 있나 검사를한다
		
		log.info(signupDto.toString());
		return "auth/signin";
	}
}
