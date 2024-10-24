package com.koreait.exam.springbatch_app_10.service;

import com.koreait.exam.springbatch_app_10.app.product.entity.Product;
import com.koreait.exam.springbatch_app_10.app.product.service.ProductService;
import com.koreait.exam.springbatch_app_10.app.song.entity.Song;
import com.koreait.exam.springbatch_app_10.app.song.service.SongService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class ProductServiceTests {
    @Autowired
    private SongService songService;
    @Autowired
    private ProductService productService;
    @Test
    @DisplayName("상품 등록")
    void t1() {
        Song song = songService.findById(1).get();
        Product product = productService.create(song, "제목 1", 2_300);
        assertThat(product).isNotNull();
        assertThat(product.getTitle()).isEqualTo("제목 1");
        assertThat(product.getPrice()).isEqualTo(2_300);
    }
    @Test
    @DisplayName("상품 수정")
    void t2() {
        Product product = productService.findById(1).get();
        productService.modify(product, "제목 1-1", 9_900);
        assertThat(product).isNotNull();
        assertThat(product.getTitle()).isEqualTo("제목 1-1");
        assertThat(product.getPrice()).isEqualTo(9_900);
    }
}