package com.cos.photogramstart.handler.ex;

import java.util.Map;

public class CustomValidationApiException extends RuntimeException{
	
	//객체구분
	private static final long serialVersionUID = 1L;
	
	//private String message;
	private Map<String, String> errorMap;
	

	
	
	
	//메세지를 알아서 부모한테 던지면 알아서 받기 때문에 message에 대한 getter는 안만들어도된다
	public CustomValidationApiException(String message, Map<String, String> errorMap) {
		super(message);
		//this.message = message;
		//System.out.println(errorMap);
		this.errorMap = errorMap;
	}

	//매개변수 하나인 생성자
	public CustomValidationApiException(String message) {
		super(message);
	}
	
	
	public Map<String, String> getErrorMap() {
		return errorMap;
	}
	
	

}
