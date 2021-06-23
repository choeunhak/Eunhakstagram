package com.cos.photogramstart.web.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CMRespDto<T> {
	

	private int code;//1은 성공, -1은 실패
	private String message;
	//private Map<String, String> errorMap;
	//MAP이 아닌 다른 데이터타입을 리턴시키기 위해 제네릭 활용
	private T data;
}