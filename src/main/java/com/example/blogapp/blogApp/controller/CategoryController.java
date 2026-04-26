package com.example.blogapp.blogApp.controller;

import com.example.blogapp.blogApp.service.CategoryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;


    //contructor injection automatic:
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public void createCateogory() {
        this.categoryService.create();
    }


}
