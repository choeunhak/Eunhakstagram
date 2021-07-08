package com.cos.photogramstart.web.api;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.comment.Comment;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.service.CommentService;
import com.cos.photogramstart.web.dto.CMRespDto;
import com.cos.photogramstart.web.dto.comment.CommentDto;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Controller// jsp라 그런듯...restcontroller를 controller로 바꿔도 잘동작한다..
public class CommentApiController {
	
	
	private final CommentService commentService;
	
	@PostMapping("/api/comment")
	public ResponseEntity<?> commentSave(@AuthenticationPrincipal PrincipalDetails principalDetails, @Valid @RequestBody CommentDto commentDto, BindingResult bindingResult){//@Requestbody는 json형태를 받기위함, 없으면 그냥 키벨류형태만받는다
//System.out.println(commentDto); //@RequestBody 가없으면 댓글을 받지를못한다...,,,
		
//		if(bindingResult.hasErrors()) {
//			Map<String, String> errorMap = new HashMap<>();
//			
//			for(FieldError error:bindingResult.getFieldErrors()) {
//				errorMap.put(error.getField(), error.getDefaultMessage());
//				//System.out.println(error.getDefaultMessage());
//			}
//			//return "오류남";
//			throw new CustomValidationApiException("유효성검사실패함", errorMap);
//		}
		
		//위의 코드 aop폴더로 옮김
		
		Comment comment = commentService.댓글쓰기(commentDto.getContent(), commentDto.getImageId(), principalDetails.getUser().getId());
		return new ResponseEntity<>(new CMRespDto<>(1, "댓글쓰기 성공", comment), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/api/comment/{id}")
	public ResponseEntity<?> commentDelete(@PathVariable int id){
		
		commentService.댓글삭제(id);
		return new ResponseEntity<>(new CMRespDto<>(1, "댓글삭제 성공", null), HttpStatus.OK);
	}

}
