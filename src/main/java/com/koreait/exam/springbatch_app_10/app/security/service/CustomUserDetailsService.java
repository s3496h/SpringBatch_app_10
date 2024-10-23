package com.koreait.exam.springbatch_app_10.app.security.service;

import com.koreait.exam.springbatch_app_10.app.member.entity.Member;
import com.koreait.exam.springbatch_app_10.app.member.repository.MemberRepository;
import com.koreait.exam.springbatch_app_10.app.security.dto.MemberContext;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor // Lombok 애너테이션 final 이 붙은 생성자를 자동 생성
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;// final 이 붙은 객체는 불변성의 성질을 가짐
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { // 사용자 인증 정보 조회
       // (데이터의 출처 = Database) -> Spring security 에서 사용가능하게 변환
        Member member = memberRepository.findByUsername(username).get();
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("MEMBER"));// 권한 부여 권한 객체는 SimpleGrantedAuthority 를 가져다 씀
        return new MemberContext(member, authorities);
    }
}
