package com.cos.photogramstart.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.service.AuthService;
import com.cos.photogramstart.web.dto.auth.SignupDto;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor//final이 걸려있는 모든애들한테 생성자를 만들어줌, DI를 편하게할수있음
@Controller
public class AuthController {

	
	//@Autowired
	private final AuthService authService;
	//IOC를 위해 autowired 말고 아래와 같이 생성자를 만들어도됨
	
//	public AuthController(AuthService authService) {
//		this.authService=authService;
//	}
	
	private static final Logger log = LoggerFactory.getLogger(AuthController.class);

	@GetMapping("/auth/signin")
	public String signinForm() {
		return "auth/signin";	
	}
	
	@GetMapping("/auth/signup")
	public String signupForm() {
		return "auth/signup";
	}
	
	//회원가입버튼을 누르면 -> auth/signup으로간다->그후에 csrf토큰으로 막힌다->그래서 disable시킴
	@PostMapping("/auth/signup")
	public String signup(SignupDto signupDto) {
		//이렇게만 하면 403에러가 뜸
		//security가 csrf로 1차적으로막는거다
		//토큰이 있나 검사를한다
		
		log.info(signupDto.toString());
		
		
		User user = signupDto.toEntity();
		
		User userEntity = authService.회원가입(user);
		System.out.println(userEntity);
		log.info(user.toString());
		return "auth/signin";
	}
}
