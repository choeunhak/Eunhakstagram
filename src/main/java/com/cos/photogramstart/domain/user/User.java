package com.cos.photogramstart.domain.user;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {
	
	@Id//pk설정
	@GeneratedValue(strategy = GenerationType.IDENTITY)//pk자동증가시키기
	private int id; 
	
	@Column(length = 30, unique = true)//unique 제약조건
	private String username;
	
	@Column(nullable=false)
	private String password;
	
	@Column(nullable=false)
	private String name; // 이름
	private String website; // 자기 홈페이지
	private String bio; // 자기소개
	
	@Column(nullable=false)
	private String email;
	private String phone;
	private String gender;
	
	private String profileImageUrl;
	
	private String role; // USER, ADMIN
	
	
	public LocalDateTime createDate;
	
	@PrePersist//db에 insert되기 직전에 실행
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}

}
