package com.cos.photogramstart.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.domain.comment.Comment;
import com.cos.photogramstart.domain.comment.CommentRepository;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.handler.ex.CustomApiException;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class CommentService {

private final CommentRepository commentRepository;
private final UserRepository userRepository;

	
	@Transactional
	public Comment 댓글쓰기(String content, int imageId, int userId) {
		
		//팁->이렇게하면 쿼리를 안짜고 (디비에서 select하기) id값만 담아서 insert할수있따.///가짜객체만들기
		//대신 return 시에 image객체와 user는 당연히 id값만 가지고있따.
		//세션값은 좀 위험할수있따.
		Image image = new Image();
		image.setId(imageId);
		
		User userEntity = userRepository.findById(userId).orElseThrow(()->{
			throw new CustomApiException("유저 아이디를 찾을 수 없습니다.");
		});
		
//		User user = new User();
//		user.setId(userId);
		
		Comment comment = new Comment();
		comment.setContent(content);
		
		comment.setImage(image);
		comment.setUser(userEntity);
		
		return commentRepository.save(comment);
	}
	
	@Transactional
	public void 댓글삭제(int id) {
		
		try {
			commentRepository.deleteById(id);
		}catch(Exception e) {
			throw new CustomApiException(e.getMessage());
		}
	}
	
	
}
