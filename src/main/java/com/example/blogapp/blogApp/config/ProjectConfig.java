package com.example.blogapp.blogApp.config;

import com.example.blogapp.blogApp.utils.ArticleModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfig {
    @Bean
    public ArticleModelMapper articleModelMapper() {
        return new ArticleModelMapper();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
