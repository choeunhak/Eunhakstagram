package com.cos.photogramstart.web.dto.subscribe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubscribeDto {
	private int userId;
	private String username;
	private String profileImageUrl;
	private Integer subscribeState; // mariadb에서는 Integer
	private Integer equalState;
}
