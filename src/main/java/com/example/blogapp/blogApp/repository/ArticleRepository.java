package com.example.blogapp.blogApp.repository;

import com.example.blogapp.blogApp.entity.Article;
import com.example.blogapp.blogApp.entity.Category;
import com.example.blogapp.blogApp.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    //custom methods
    List<Article> findByCategory(Category category);
    List<Article> findByUser(User user);
    List<Article> findByCategoryAndUser(Category category, User user);

}