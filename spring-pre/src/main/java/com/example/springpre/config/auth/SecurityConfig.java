package com.example.springpre.config.auth;

import com.example.springpre.entity.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()    //URL별 권환 관리 설정 옵션의 시작점, 선언 되어야만
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**",  "/profile", "/hello", "/hello/**").permitAll()  //권한 관리 대상 지정, permitAll() 전체 열람 권한
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())    //권한 관리 대상 지정, user 만 열람 가능
                .anyRequest().authenticated()   // 남은 url 전부.  authenticated : 인증된 사용자들에게만 허용
                .and()
                .logout()
                .logoutSuccessUrl("/")      //로그아웃시 진입점
                .and()
                .oauth2Login()      // 로그인 기능에 대한 여러 진입점
                .userInfoEndpoint()     // OAuth2 로그인 성공 후 사용자 정보를 가져올 때의 설정들 담당
                .userService(customOAuth2UserService);  // 로그인 성공 시 후속 조치를 위한 service 인터페이스 구현체를 등록 -> 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능 명시
    }
}
