package com.koreait.exam.springbatch_app_10.app.handler;

import com.koreait.exam.springbatch_app_10.app.security.dto.MemberContext;
import com.koreait.exam.springbatch_app_10.util.Ut;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class CustomAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler { // 인증 성공시 동작을 컨트롤 (성공시 어디로갈지, 세션은 어떻게 사용할지 등)
    private final RequestCache requestCache = new HttpSessionRequestCache();
    // 사용자가 인증X  상태에서 접근하려고 했던 요청 기억 -> 인증ㅇ -> 리다이렉트
    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
   // 인증ㅇ -> 다른 URL 로 보내주기위함
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        clearSession(request); // 로그인 실패시에 남아있는 에러 정보를 세션에서 제거
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        /**
         * prevPage가 존재하는 경우 = 사용자가 직접 /auth/login 경로로 로그인 요청
         * 기존 Session의 prevPage attribute 제거
         */
        String prevPage = (String) request.getSession().getAttribute("prevPage");
        if (prevPage != null) {
            request.getSession().removeAttribute("prevPage");
        }// 사용자가 로그인 하기 전의 페이지 => prevPage 로 세션에 저장 되어 있는경우 확인하고 삭제 하기위해 사용
        // 기본 URI
        String url = "/";
        /**
         * savedRequest 존재하는 경우 = 인증 권한이 없는 페이지 접근
         * Security Filter가 인터셉트하여 savedRequest에 세션 저장
         */
        if (savedRequest != null) { // 사용자가 보호된 페이지에 접근시에 사용자가 보냈던 요청이 savedRequest에 저장
            url = savedRequest.getRedirectUrl();
        } else if (prevPage != null && prevPage.length() > 0) {
            // 회원가입 -> 로그인으로 넘어온 경우 "/"로 redirect
            if (prevPage.contains("/member/join")) {
                url = "/"; // 회원가입 -> 로그인으로 넘어온 경우 "/(home)"로 redirect
            } else {
                url = prevPage; //  다른페이지에서 로그인 하면 직전페이지로 리다이렉트
            }
        }
        MemberContext memberContext = (MemberContext) authentication.getPrincipal();
        url = Ut.url.modifyQueryParam(url, "msg", memberContext.getName() + "님 환영합니다.");
        redirectStrategy.sendRedirect(request, response, url);
    }


    // 로그인 실패 후 성공 시 남아있는 에러 세션 제거
    // 실패상태가 성공후 영향x
    protected void clearSession(HttpServletRequest request) { //
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        }
    }
}
