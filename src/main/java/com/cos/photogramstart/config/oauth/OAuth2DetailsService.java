package com.cos.photogramstart.config.oauth;

import java.util.Map;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;



@RequiredArgsConstructor
@Service
public class OAuth2DetailsService extends DefaultOAuth2UserService{

	
	
	private final UserRepository userRepository;
	
	//여기서 회원정보를 받고 처리하면끝난다.
	
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		
		OAuth2User oAuth2User = super.loadUser(userRequest);
		//System.out.println(oAuth2User.getAttributes());
		
		Map<String, Object> userInfo = oAuth2User.getAttributes();
		

		String username = "facebook_"+(String)userInfo.get("id");
		String email = (String)userInfo.get("email");
		String name = (String)userInfo.get("name");
		String password = new BCryptPasswordEncoder().encode(UUID.randomUUID().toString());
		
		
		
		User userEntity = userRepository.findByUsername(username);
		
		if(userEntity==null) { //최초로그인
			User user = User.builder()
					.username(username)
					.password(password)
					.email(email)
					.name(name)
					.role("ROLE_USER")
					.build();
			
			return new PrincipalDetails(userRepository.save(user), oAuth2User.getAttributes());// 여기서 oauth2userdetails 클래스만들지 마라->나중에 principaldetail랑 꼬여서 엄청힘들어진다!,principaldetails에 만들어라!
		}else { //이미가입되어있음
			return new PrincipalDetails(userEntity, oAuth2User.getAttributes());
		}
		
		//oauth 구분할수있게됨
		
		
		
		
	}
	
}
