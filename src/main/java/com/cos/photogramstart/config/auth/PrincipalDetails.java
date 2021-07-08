package com.cos.photogramstart.config.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.cos.photogramstart.domain.user.User;

import lombok.Data;


@Data
public class PrincipalDetails implements UserDetails, OAuth2User{

	
	private static final long serialVersionUID=1L;
	
	
	private User user;
	private Map<String, Object> attributes;
	
	public PrincipalDetails(User user) {
		this.user=user;
	}
	
	public PrincipalDetails(User user, Map<String, Object> attributes) {
		this.user=user;
	}
	
	//권한이 한개가 아닐수가있다!!! 3개이상의 권한 우리는 하나만할거다
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collectors = new ArrayList<>();
		collectors.add(()-> {return user.getRole();});
		return collectors;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {//만료가되엇나

		return true ;
	}

	@Override
	public boolean isAccountNonLocked() {//잠겻나, false면 로그인안됨
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {//활성화되어있니, 1년정도로그인안했는지 확인
		return true;
	}
	
	///////////////////////////////////////

	@Override
	public Map<String, Object> getAttributes() {
		// TODO Auto-generated method stub
		return attributes;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return (String)attributes.get("name");
	}
	
}