package com.cos.photogramstart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity //해당파일로 security 활성화
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	
	//비밀번호 암호화
	@Bean//bean으로등록, 해시함수
	public BCryptPasswordEncoder encode() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//super.configure(http);//security library에서 여기서 계속 가로채는거임
		//위의 코드 지우면 기존 시큐리티가 가지고 있는 기능 비활성화
		
		
		http.csrf().disable();//csrf 비활성화
		http.authorizeRequests()
			.antMatchers("/", "/user/**", "/image/**", "/subscribe/**, /comment/**").authenticated()
			.anyRequest().permitAll()//모든요청허용
			//위 주소외의 기타주소는 들어가진다. ex /auth 등
			//자동으로 로그인화면으로가고싶다
			.and()
			.formLogin()
			.loginPage("/auth/signin")//GET //user에 인증이 안되어있으면 이페이지를 던져줌
			.loginProcessingUrl("/auth/signin")//POST//로그인을 진행하려고하면 여기를 스프링시큐리티가 진행한다
			.defaultSuccessUrl("/");//로그인성공시 가는페이지
		
	}
}
