package com.cos.photogramstart.service;

import javax.transaction.Transactional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Transactional
	public User 회원수정(int id, User user) {
		
		//1. 서비스에서는 영속화를해야한다
		User userEntity = userRepository.findById(id).get();
		
		
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
