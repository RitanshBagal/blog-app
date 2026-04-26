package com.example.blogapp.blogApp.service;

import com.example.blogapp.blogApp.dto.ArticleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticleService {

//    List<ArticleDto> getAll();
    Page<ArticleDto> getAll(Pageable pageable);
    ArticleDto getArticle(Long articleId);
    ArticleDto createArticle(ArticleDto articleDto);
    ArticleDto updateArticle(ArticleDto articleDto, Long articleId );
    void deleteArticle(Long articleId);

    List<ArticleDto> getArticleOfCategory(Long categoryId);

    List<ArticleDto> getArticleOfUser(Long userId);

}