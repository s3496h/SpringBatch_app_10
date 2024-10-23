package com.koreait.exam.springbatch_app_10.app.member.controller;

import com.koreait.exam.springbatch_app_10.app.member.service.MemberService;
import com.koreait.exam.springbatch_app_10.app.member.form.JoinForm;
import com.koreait.exam.springbatch_app_10.util.Ut;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @PreAuthorize("isAnonymous()") // 인증되지 않은유저만 실행 가능 // 로그인 x
    @GetMapping("/login")
    public String showLogin(HttpServletRequest request) {
        String uri = request.getHeader("Referer");
        if (uri != null && !uri.contains("/member/login")) {
            request.getSession().setAttribute("prevPage", uri);
        }
        return "member/login";
    }

    @PreAuthorize("isAnonymous()")// 인증되지 않은유저만 실행 가능 // 로그인 x
    @GetMapping("/join")
    public String showJoin() {

        return "member/join";
    }

    @PreAuthorize("isAnonymous()")// 인증되지 않은유저만 실행 가능 // 로그인 x
    @PostMapping("/join")
    public String join(@Valid JoinForm joinForm) { // 회원가입폼 에서 입력한 정보를 유효성 검사(@valid 애너테이션이 유효성검사를 해줌)
        memberService.join(joinForm.getUsername(), joinForm.getPassword(), joinForm.getEmail()); //  회원가입 로직
        return "redirect:/member/login?msg=" + Ut.url.encode("회원가입이 완료되었습니다."); // 회원가입 완료후 리다이렉트 후 메세지 보여줌
    }
}
