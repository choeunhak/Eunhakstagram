package com.cos.photogramstart.web.api;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.service.UserService;
import com.cos.photogramstart.web.dto.CMRespDto;
import com.cos.photogramstart.web.dto.user.UserUpdateDto;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
public class UserApiController {

	
	private final UserService userService;
	
	    @PutMapping("/api/user/{id}")
	    public CMRespDto<?> update(@PathVariable int id, 
	    		@Valid UserUpdateDto userUpdateDto, 
	    		BindingResult bindingResult, //꼭 @Valid 다음에 있어야한다
	    		@AuthenticationPrincipal PrincipalDetails principalDetails) {
	    	
	    	if(bindingResult.hasErrors()) {
				Map<String, String> errorMap = new HashMap<>();
				
				for(FieldError error:bindingResult.getFieldErrors()) {
					errorMap.put(error.getField(), error.getDefaultMessage());
					//System.out.println(error.getDefaultMessage());
				}
				//return "오류남";
				throw new CustomValidationApiException("유효성검사실패함", errorMap);
			}else {
				
				//System.out.println(userUpdateDto);
		        User userEntity = userService.회원수정(id, userUpdateDto.toEntity());
		        principalDetails.setUser(userEntity);
		        return new CMRespDto<>(1, "회원수정완료", userEntity);
				
			}
	    	
	    	
	    	
	        
	}
}
