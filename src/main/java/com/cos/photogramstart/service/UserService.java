package com.cos.photogramstart.service;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.handler.ex.CustomException;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@Transactional(readOnly=true)//리드오니가 jpa를 일을 덜하게 해줄수있다(계속 감지하지않게함)
	public User 회원프로필(int userId) {
		
		User userEntity = userRepository.findById(userId).orElseThrow(()->{
			throw new CustomException("해당 프로필 페이지는 없는 페이지입니다.");
		});
		
		return userEntity;
	}
	
	@Transactional
	public User 회원수정(int id, User user) {
		
		//1. 서비스에서는 영속화를해야한다, 람다로 변경
		User userEntity = userRepository.findById(id).orElseThrow(() -> {
			return new CustomValidationApiException("찾을수없는id입니다.");
		});
		
		
		String rawPassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		
		//exception처리나중에
		//없으면 null이리턴되기때문에 optional 반환하는데 3가지 조건을 할수있따
		//1. 찾았따(get), 2. 못찾았따(exception orelseThrow함수), 3. orelse크게안중요함 
		
		//2. 영속화된 오브젝트를 수정해야한다 더티체킹으로 업데이트완료
		
		userEntity.setName(user.getName());
		userEntity.setBio(user.getBio());
		userEntity.setWebsite(user.getWebsite());
		userEntity.setGender(user.getGender());
		userEntity.setPhone(user.getPhone());
		userEntity.setPassword(encPassword);
		
		return userEntity;
	}
		// username, email 수정 불가
//		
//
//		userEntity.setName(user.getName());
//		userEntity.setBio(user.getBio());
//		userEntity.setWebsite(user.getWebsite());
//		userEntity.setGender(user.getGender());
//		String rawPassword = user.getPassword();
//		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
//
//		// 비밀번호를 수정시 안넘기면 기존 비번 유지
//		if(!user.getPassword().equals("")) {
//			userEntity.setPassword(encPassword);
//		}
//	
//		
//		return userEntity;
//	} // 더티체킹
}
