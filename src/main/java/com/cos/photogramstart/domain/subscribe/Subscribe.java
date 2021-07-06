package com.cos.photogramstart.domain.subscribe;

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

import com.cos.photogramstart.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(//unique 제약조건 두개에 복합적으로 걸기
		name="subscribe",
		uniqueConstraints={
			@UniqueConstraint(
				name = "subscribe_uk",
				columnNames={"fromUserId","toUserId"}
			)
		}
	)
public class Subscribe {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@JoinColumn(name = "fromUserId")//이렇게 컬럼명 만들어라 _id이렇게하지말고
	@ManyToOne
	private User fromUser;  // ~~로부터  (1)
	
	@JoinColumn(name = "toUserId")
	@ManyToOne
	private User toUser; // ~~를  (3)
	
	public LocalDateTime createDate;
	
	@PrePersist//db에 insert되기 직전에 실행
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}

}
