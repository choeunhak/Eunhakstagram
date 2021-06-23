package com.cos.photogramstart.domain.user;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {
	
	@Id//pk설정
	@GeneratedValue(strategy = GenerationType.IDENTITY)//pk자동증가시키기
	private int id; 
	
	@Column(length = 30, unique = true)
	private String username;
	private String password;
	
	private String name; // 이름
	private String website; // 자기 홈페이지
	private String bio; // 자기소개
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
