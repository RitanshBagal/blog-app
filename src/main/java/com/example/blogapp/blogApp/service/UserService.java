package com.example.blogapp.blogApp.service;

import com.example.blogapp.blogApp.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserDto registerUser(UserDto userDto);
    UserDto findByEmail(String email);
}
