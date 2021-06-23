package com.cos.photogramstart.web;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomValidationException;
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
	
	//회원가입버튼을 누르면 -> auth/signup
	@PostMapping("/auth/signup")
	public String signup(@Valid SignupDto signupDto, BindingResult bindingResult) {//@responsebody는 파일이 아닌 데이터를응답해준다
		//bindingresult는 에러를 받을수있다
		
		
		
		//이렇게만 하면 403에러가 뜸
		//security가 csrf로 1차적으로막는거다
		//토큰이 있나검사를한다
		if(bindingResult.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
			
			for(FieldError error:bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
				//System.out.println(error.getDefaultMessage());
			}
			//return "오류남";
			throw new CustomValidationException("유효성검사실패함", errorMap);
		}else {
			//log.info(signupDto.toString());
			User user = signupDto.toEntity();
			
			User userEntity = authService.회원가입(user);
			//System.out.println(userEntity);
			//log.info(user.toString());
			return "auth/signin";
		}
		
		
	}
}
