package com.cos.photogramstart.domain.user;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import com.cos.photogramstart.domain.image.Image;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
	
	@Column(length = 100, unique = true)//unique 제약조건
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
	
	//양방향매핑하는것! maappedby 주인이아니다
	//나는 연관관계의 주인이 아니기때무네 테이블에 컬럼을 만들지 말고, user를 select할때 해당 userid로 등록된 이미지들을 다 가져와라, //onetomany는 fetchType이 있따 레이지로딩, 즉시로딩, 
	// lazy는 user를 select할때 해당 userid로 등로된 이미지들을 가져오지마 대신 getImages함수호출될때만 가져온다.,  eager는 user를 select할때 해당 userid로 등로된 이미지들을전부 조인해서 가져와
	@OneToMany(mappedBy="user", fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"user"})//내부 user무시하고 파싱함,, 자주사용되니까 암기,, jpa 무한참조해결...!!!
	private List<Image> images;
	
	
	public LocalDateTime createDate;
	
	@PrePersist//db에 insert되기 직전에 실행
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}

	
	//aop에서 sysout할 때 무한참조에러해결을위해 images 제거
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", name=" + name + ", website="
				+ website + ", bio=" + bio + ", email=" + email + ", phone=" + phone + ", gender=" + gender
				+ ", profileImageUrl=" + profileImageUrl + ", role=" + role + ", createDate="
				+ createDate + "]";
	}
	
	
	
	

}
