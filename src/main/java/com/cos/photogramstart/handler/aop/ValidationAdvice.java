package com.cos.photogramstart.handler.aop;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.handler.ex.CustomValidationException;


@Component
@Aspect
public class ValidationAdvice {
	
	
	@Around("execution(* com.cos.photogramstart.web.api.*Controller.*(..))")//before, after도 있음 실행직전 후에 실행후에 실행해줌, "에는 주소적기, 패키지 주소, controller다음엔 메소드,,..은 뭐든함수가 작동할때실행됨
	public Object apiAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {//joinpoint는 commentsave함수가 실행됐을때  내부 정보에 접근할수있다..!!!,, 내부에 접근시켜줌!!
		
		//proceedingJoinPoint는 profile함수의 모든곳에 접근할수있는변수
		//메소드profile함수(등등)보다 먼저실행, commentsave도 마찬가지겠지?
		System.out.println("webapi컨트롤러==============");
		
		
		//모든 매개변수 접근 가능
		Object[] args = proceedingJoinPoint.getArgs();//매개변수 뽑기
		for(Object arg:args) {
			//System.out.println(arg);
			if(arg instanceof BindingResult) {
				System.out.println("유효성 검사를 하는 함수입니다.");
				BindingResult bindingResult=(BindingResult)arg; //다운캐스팅
				if(bindingResult.hasErrors()) {
					Map<String, String> errorMap = new HashMap<>();
					
					for(FieldError error:bindingResult.getFieldErrors()) {
						errorMap.put(error.getField(), error.getDefaultMessage());
						//System.out.println(error.getDefaultMessage());
					}
					//return "오류남";
					throw new CustomValidationApiException("유효성검사실패함", errorMap);
				}
				
			}
		}
		return proceedingJoinPoint.proceed();////return은 proceed가 그 함수로 다시 돌아가라, profile실행
	}
	
	@Around("execution(* com.cos.photogramstart.web.*Controller.*(..))")
	public Object advice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		
		System.out.println("web컨트롤러==============");
		Object[] args = proceedingJoinPoint.getArgs();//매개변수 뽑기
		for(Object arg:args) {
			//System.out.println(arg);
			
			if(arg instanceof BindingResult) {
				//System.out.println("유효성 검사를 하는 함수입니다.");
				BindingResult bindingResult=(BindingResult)arg; //다운캐스팅
				
				
				if(bindingResult.hasErrors()) {
					Map<String, String> errorMap = new HashMap<>();
					
					for(FieldError error:bindingResult.getFieldErrors()) {
						errorMap.put(error.getField(), error.getDefaultMessage());
						//System.out.println(error.getDefaultMessage());
					}
					//return "오류남";
					throw new CustomValidationException("유효성검사실패함", errorMap);
				}
				
			}
		}
		
		
		
		return proceedingJoinPoint.proceed();
	}

}
