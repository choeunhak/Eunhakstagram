package com.cos.photogramstart.domain.subscribe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SubscribeRepository extends JpaRepository<Subscribe, Integer>{
	
	
	@Modifying//네이티브 쿼리 적용되게
	@Query(value = "INSERT INTO subscribe(fromUserId, toUserId, createDate) VALUES(:fromUserId, :toUserId, now())", nativeQuery = true)//:은 파라미터를 받는것이다
	void mSubscribe(int fromUserId, int toUserId); 
	
	@Modifying
	@Query(value = "DELETE FROM subscribe WHERE fromUserId = :fromUserId AND toUserId = :toUserId", nativeQuery = true)
	void mUnSubscribe(int fromUserId, int toUserId);
	
	@Query(value = "SELECT count(*) from subscribe where fromUserId = :principalId AND toUserId = :userId", nativeQuery = true)
	int mSubscribeState(int principalId, int userId);
	
	@Query(value = "SELECT count(*) from subscribe where fromUserId = :userId", nativeQuery = true)
	int mSubscribeCount(int userId);
	
	//참고
	// prepareStatement updateQuery() => -1 0 1
	
}
