package com.cos.photogramstart.handler.ex;

import java.util.Map;

public class CustomApiException extends RuntimeException{
	
	//객체구분
	private static final long serialVersionUID = 1L;

	//매개변수 하나인 생성자
	public CustomApiException(String message) {
		super(message);
	}
	

}
