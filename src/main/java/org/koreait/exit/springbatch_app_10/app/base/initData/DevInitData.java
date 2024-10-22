package org.koreait.exit.springbatch_app_10.app.base.initData;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
@Configuration
@Profile("dev")
public class DevInitData implements InitDataBefore {
    @Bean
    CommandLineRunner initData() {
        return args -> {
            before();
        };
    }
}