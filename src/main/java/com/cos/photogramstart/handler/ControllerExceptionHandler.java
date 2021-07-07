package com.cos.photogramstart.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.handler.ex.CustomException;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.util.Script;
import com.cos.photogramstart.web.dto.CMRespDto;

@RestController
@ControllerAdvice//에러처리 낚아채게 handler 등록
public class ControllerExceptionHandler {

	
	//자바스크립트 리턴, Script.back(e.getErrorMap().toString())하면됨)
	@ExceptionHandler(CustomValidationException.class)//모든 runtime익셉션 낚아챔
	public String validationException(CustomValidationException e) {//뭘 return할지 모르겠으면 그냥 <?>이렇게하면된다
		
		if(e.getErrorMap()==null) {
			return Script.back(e.getMessage());
		}else {
			return Script.back(e.getErrorMap().toString());
		}
		
		
	}
	
	//data 리턴
//	@ExceptionHandler(CustomValidationApiException.class)//모든 runtime익셉션 낚아챔
//	public CMRespDto<Map<String, String>> validationApiException(CustomValidationApiException e) {//뭘 return할지 모르겠으면 그냥 <?>이렇게하면된다
//		return new CMRespDto(-1, e.getMessage(), e.getErrorMap());
//		
//	}
	
	@ExceptionHandler(CustomValidationApiException.class)//모든 runtime익셉션 낚아챔
	public ResponseEntity<?> validationApiException(CustomValidationApiException e) {//뭘 return할지 모르겠으면 그냥 <?>이렇게하면된다
		return new ResponseEntity<>(new CMRespDto<>(-1, e.getMessage(), e.getErrorMap()), HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(CustomApiException.class)//모든 runtime익셉션 낚아챔
	public ResponseEntity<?> apiException(CustomApiException e) {//뭘 return할지 모르겠으면 그냥 <?>이렇게하면된다
		return new ResponseEntity<>(new CMRespDto<>(-1, e.getMessage(), null), HttpStatus.BAD_REQUEST);
		
	}
	
		@ExceptionHandler(CustomException.class)
		public String exception(CustomException e) {
			return Script.back(e.getMessage());
			
			
		}
	
	
}
