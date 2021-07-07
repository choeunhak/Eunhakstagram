package com.cos.photogramstart.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.image.ImageRepository;
import com.cos.photogramstart.web.dto.Image.ImageUploadDto;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class ImageService {
	
	private final ImageRepository imageRepository;
	
	
	@Transactional(readOnly = true)
	public Page<Image> 이미지스토리(int principalId, Pageable pageable){
		Page<Image> images = imageRepository.mStory(principalId, pageable);
		
		
		//image에 좋아요 상태 담기 
		// 좋아요 하트 색깔 로직 + 좋아요 카운트 로직
			images.forEach((image)-> {
				
				
				//2번이 로그인하고 이미지들을 쫙 들고오고  이미지들을 돌려서 좋아요정보를 봅아내서 내가 좋아요한건지만알면된다
				int likeCount = image.getLikes().size();
				image.setLikeCount(likeCount);
				
				image.getLikes().forEach((like)->{
					if(like.getUser().getId() == principalId) {//해당이미지 좋아요한사람들을 찾아서 현재 로긴한사람이 좋아요한것인지 비교
						image.setLikeState(true);
					}
				});
			});
			
		
		return images;
	}
	
	
	@Value("${file.path}")
	private String uploadFolder;
	
	
	@Transactional
	public void 사진업로드(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails) {
		
		UUID uuid = UUID.randomUUID();//uuid는 고유성이 보장되는 id를 만들게해준다
		String imageFileName = uuid+"_"+imageUploadDto.getFile().getOriginalFilename();//파일명
		//System.out.println("파일명 : "+imageFileName);
		
		Path imageFilePath = Paths.get(uploadFolder+imageFileName);
		//System.out.println("파일패스 : "+imageFilePath);
		try {
			Files.write(imageFilePath, imageUploadDto.getFile().getBytes());//바이트화해서넣어야함
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		// 참고 :  Image 엔티티에 Tag는 주인이 아니다. Image 엔티티로 통해서 Tag를 save할 수 없다.
//		
//		// 1. Image 저장
		Image image = imageUploadDto.toEntity(imageFileName, principalDetails.getUser());
		imageRepository.save(image);
		
//		// 2. Tag 저장
//		List<Tag> tags = TagUtils.parsingToTagObject(imageReDto.getTags(), imageEntity);
//		tagRepository.saveAll(tags);
		
	}

}
