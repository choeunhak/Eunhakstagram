package com.cos.photogramstart.config.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class PrincipalDetailsService implements UserDetailsService {
	
	private final UserRepository userRepository;

	//httpbody에 username과 password를 ioc 컨테이너에 떠있는 userDetailservice에 던져서 로그인진행
	//이걸로 로그인을 진행한다
	
	//같으면 덮어씌운다(userdetailservice->principaldetailservice로 변한것)
	//아래 loaduserbyusername 실행됨
	
	//1. 패스워드는 알아서 체크하니까 신경안써도된다
	//2. 리턴이 잘되면 내부적으로 세션을 만들어준다
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//여기서 무엇을할까?
		//username이 있는지없는지만 체크하면된다
		
		User userEntity = userRepository.findByUsername(username);
		if(userEntity == null) {
			return null;
		}else {
			return new PrincipalDetails(userEntity); // SecurityContextHolder => Authentication 객체 내부에 담김.
		}
		
	}

}
