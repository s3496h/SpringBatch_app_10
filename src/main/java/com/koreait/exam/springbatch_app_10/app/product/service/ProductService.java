package com.koreait.exam.springbatch_app_10.app.product.service;

import com.koreait.exam.springbatch_app_10.app.product.entity.Product;
import com.koreait.exam.springbatch_app_10.app.product.repository.ProductRepository;
import com.koreait.exam.springbatch_app_10.app.song.entity.Song;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {
    private final ProductRepository productRepository;
    @Transactional
    public Product create(Song song, String title, int price) {
        Product product = Product.builder()
                .song(song)
                .title(title)
                .author(song.getAuthor())
                .price(price)
                .build();
        productRepository.save(product);
        return product;
    }
    @Transactional
    public void modify(Product product, String newTitle, int newPrice) {
        product.setTitle(newTitle);
        product.setPrice(newPrice);
        productRepository.save(product);
    }
    public Optional<Product> findById(long id) {
        return productRepository.findById(id);
    }
}