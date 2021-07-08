package com.cos.photogramstart.web.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.service.SubscribeService;
import com.cos.photogramstart.service.UserService;
import com.cos.photogramstart.web.dto.CMRespDto;
import com.cos.photogramstart.web.dto.subscribe.SubscribeDto;
import com.cos.photogramstart.web.dto.user.UserUpdateDto;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
public class UserApiController {

	
	private final UserService userService;
	private final SubscribeService subscribeService;
	
	
		@PutMapping("/api/user/{principalId}/profileImageUrl") // data 리턴하는 것
		public  ResponseEntity<?> profileImageUrlUpdate(@PathVariable int principalId, MultipartFile profileImageFile, @AuthenticationPrincipal PrincipalDetails principalDetails) {
			
			User userEntity = userService.회원프로필사진변경(principalId, profileImageFile);
			
			principalDetails.setUser(userEntity);//세션변경
	
			return new ResponseEntity<>(new CMRespDto<>(1, "프로필사진변경 성공", null), HttpStatus.OK);
		}
	
	
		@GetMapping("/api/user/{pageUserId}/subscribe") // data 리턴하는 것
		public ResponseEntity<?> subscribeList(@PathVariable int pageUserId, @AuthenticationPrincipal PrincipalDetails principalDetails) {
			List<SubscribeDto> subscribeDto = subscribeService.구독리스트(principalDetails.getUser().getId(), pageUserId);
	
			return new ResponseEntity<>(new CMRespDto<>(1, "구독자정보리스트가져오기성공", subscribeDto), HttpStatus.OK);
		}
	
	
	    @PutMapping("/api/user/{id}")
	    public CMRespDto<?> update(@PathVariable int id, 
	    		@Valid UserUpdateDto userUpdateDto, 
	    		BindingResult bindingResult, //꼭 @Valid 다음에 있어야한다
	    		@AuthenticationPrincipal PrincipalDetails principalDetails) {
	    	
	    	
	    	//aop폴더로 옮김
//	    	if(bindingResult.hasErrors()) {
//				Map<String, String> errorMap = new HashMap<>();
//				
//				for(FieldError error:bindingResult.getFieldErrors()) {
//					errorMap.put(error.getField(), error.getDefaultMessage());
//					//System.out.println(error.getDefaultMessage());
//				}
//				//return "오류남";
//				throw new CustomValidationApiException("유효성검사실패함", errorMap);
//			}else {
//				
				//System.out.println(userUpdateDto);
		        User userEntity = userService.회원수정(id, userUpdateDto.toEntity());
		        principalDetails.setUser(userEntity);
		        return new CMRespDto<>(1, "회원수정완료", userEntity);//응답시에 uesrEntity의 모든 getter함수호출되고 json으로 파싱하여 응답함.
				
//			}
	    	
	    	
	    	
	        
	}
}
