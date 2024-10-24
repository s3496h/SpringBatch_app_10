package com.koreait.exam.springbatch_app_10.app.base.initData;

import com.koreait.exam.springbatch_app_10.app.member.service.MemberService;
import com.koreait.exam.springbatch_app_10.app.product.service.ProductService;
import com.koreait.exam.springbatch_app_10.app.song.service.SongService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")// 개발환경에서 쓸지 테스트 환경에서 사용할지 정해줌 YML파일
public class DevInitData implements InitDataBefore {
    @Bean
    CommandLineRunner initData(MemberService memberService, SongService songService, ProductService productService) {
        return args -> {
            before(memberService,songService,productService);
        };
    }
}
