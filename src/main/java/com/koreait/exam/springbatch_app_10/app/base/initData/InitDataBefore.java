package com.koreait.exam.springbatch_app_10.app.base.initData;

import com.koreait.exam.springbatch_app_10.app.member.service.MemberService;
import com.koreait.exam.springbatch_app_10.app.member.entity.Member;
import com.koreait.exam.springbatch_app_10.app.song.service.SongService;

public interface InitDataBefore {
    default void before(MemberService memberService, SongService songService) {
        Member member1 = memberService.join("user1", "1234", "user1@test.com");
        Member member2 = memberService.join("user2", "1234", "user2@test.com");

        songService.create(member1,"노래 1", "내용 1");
        songService.create(member1,"노래 2", "내용 2");
        songService.create(member2,"노래 3", "내용 3");
        songService.create(member2,"노래 4", "내용 4");
        songService.create(member1,"노래 5", "내용 5");
        songService.create(member1,"노래 6", "내용 6");
        songService.create(member2,"노래 7", "내용 7");
        songService.create(member2,"노래 8", "내용 8");
    }
}
