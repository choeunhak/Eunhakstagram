package com.cos.photogramstart.domain.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

	
//	@Modifying
//	@Query(value="INSERT INTO comment(content, imageId, userId, createDate) values(:content, :imageId, :userId, now())",nativeQuery=true)
//	Comment mSave(String content, int imageId, int userId);//네이티브 쿼리는 들어간 객체를 리턴받을수없다.
	//근데 int로 리턴받으면 나중에 댓글을 삭제할때 comment pk를 모르기때문에 삭제할수가 없다. 따라서 네이티브 쿼리를 사용할수없다..
	//그래서 jpa의 save를 써야한다.
}
