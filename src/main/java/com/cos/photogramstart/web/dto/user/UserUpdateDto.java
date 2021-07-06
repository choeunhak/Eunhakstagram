package com.cos.photogramstart.web.dto.user;

import com.cos.photogramstart.domain.user.User;

import lombok.Data;

@Data
public class UserUpdateDto {

	private String password;
	private String name; // 이름
	
	
	//위에 두개는 필수데이터인데 아래 두개는 줘도되고 안줘도된다, 그래서 아래처럼 toentity로 만드는게 위험하다 추후에 코드수정
	
	private String website; // 자기 홈페이지
	private String bio; // 자기소개
	private String phone;
	private String gender;
	
	
	public User toEntity() {
		return User.builder()
				.password(password)//공백이 들어갈 위험이 있다, validation 체크를해야한다
				.name(name)
				.website(website)
				.bio(bio)
				.phone(phone)
				.gender(gender)
				.build();
	}
}
