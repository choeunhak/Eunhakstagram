package com.cos.photogramstart.domain.image;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Transient;

import com.cos.photogramstart.domain.likes.Likes;
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
public class Image {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String caption;
	private String postImageUrl;//사진을 전송받아서 그 사진을 서버에 특정폴더에 저장, db에 그 저장 경로를 insert
	
	//FK로 저장
	@JsonIgnoreProperties({"images"})//유저가 가지고있는 이미지는 무시함
	@JoinColumn(name="userId")
	@ManyToOne(fetch=FetchType.EAGER)//eager로 가져옴
	private User user;//누가 업로드했는지를 알기위해
	
	//이미지 좋아요
	@JsonIgnoreProperties({"images"})
	@OneToMany(mappedBy="image")//레이지로딩
	private List<Likes> likes;
	
	

	@Transient//디비에 칼럼이 만들어지지않는다
	private boolean likeState;
	
	
	//이미지 댓글
	
	public LocalDateTime createDate;
	
	@PrePersist//db에 insert되기 직전에 실행
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}

	//오브젝트를 콘솔에 출력할때 문제가 될수있어서 user부분을 출력되지않게함
//	@Override
//	public String toString() {
//		return "Image [id=" + id + ", caption=" + caption + ", postImageUrl=" + postImageUrl 
//				+ ", createDate=" + createDate + "]";
//	}
	
	
	

}
