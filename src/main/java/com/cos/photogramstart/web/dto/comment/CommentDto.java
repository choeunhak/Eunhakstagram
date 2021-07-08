package com.cos.photogramstart.web.dto.comment;

import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

import lombok.Data;

@Data
public class CommentDto {
	
	//notnull notempty notblank
	
	
	@NotBlank
	private String content;
	@NotNull
	private Integer imageId;
	
	//toEntity가필요없다

}
