package com.koreait.exam.springbatch_app_10.app.base.initData;

import com.koreait.exam.springbatch_app_10.app.member.MemberService;
import com.koreait.exam.springbatch_app_10.app.member.entity.Member;

public interface InitDataBefore {
    default void before(MemberService memberService) {
        Member member1 = memberService.join("user1", "1234", "user1@test.com");
        Member member2 = memberService.join("user2", "1234", "user2@test.com");
    }
}
