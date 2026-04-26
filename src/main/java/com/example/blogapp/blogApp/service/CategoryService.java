package com.example.blogapp.blogApp.service;

import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    public void create(){
        System.out.println("category created");
    }

    public void update(){
        System.out.println("category updated");
    }
}