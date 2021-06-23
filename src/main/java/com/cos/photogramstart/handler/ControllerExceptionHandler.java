package com.cos.photogramstart.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice//에러처리 낚아채게 handler 등록
public class ControllerExceptionHandler {

	@ExceptionHandler(RuntimeException.class)//모든 runtime익셉션 낚아챔
	public String validationException(RuntimeException e) {
		return e.getMessage();
		// 깃 테스트
	}
}
