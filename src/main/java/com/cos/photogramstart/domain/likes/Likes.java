package com.cos.photogramstart.domain.likes;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.user.User;
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
@Table(//unique 제약조건 두개에 복합적으로 걸기,,//하나의 이미지에 사람이 한번만 할수있도록 중복unique키로 묶은것이다!!!!!!(내가 생각하던 에브리타임 수강평도 이런식으로 구현하면 되는듯)
		uniqueConstraints={
			@UniqueConstraint(
				name = "likes_uk",
				columnNames={"imageId","userId"}
			)
		}
	)
public class Likes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	//무한참조버그
	@ManyToOne//1번 좋아요정보를 select할때 image와 user를 eager로 가져온다(manytoone은 eager이 기본), like : image가 N:1
	@JoinColumn(name = "imageId")
	private Image image;
	
	@JsonIgnoreProperties({"images"})//좋아요 정보를 가져올때 user를 가져오는데 user가 이미지를 가져온다,이 이미지를 가져올필요가없다
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	
	
	
	public LocalDateTime createDate;
//	네이티브쿼리기 때문에 아래와 같이 날짜생성이안됨
//	@PrePersist//db에 insert되기 직전에 실행
//	public void createDate() {
//		this.createDate = LocalDateTime.now();
//	}
	



}
