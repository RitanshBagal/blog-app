package com.example.blogapp.blogApp.service.impl;

import com.example.blogapp.blogApp.dto.LoginRequest;
import com.example.blogapp.blogApp.dto.UserDto;
import com.example.blogapp.blogApp.entity.Role;
import com.example.blogapp.blogApp.entity.User;
import com.example.blogapp.blogApp.exception.ResourceNotFoundException;
import com.example.blogapp.blogApp.repository.UserRepository;
import com.example.blogapp.blogApp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Override
    public UserDto registerUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        validateUser(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.ROLE_GUEST);
        user.setEnabled(true);
        User savedUser = userRepository.save(user);

        return modelMapper.map(savedUser, UserDto.class);

    }

    @Override
    public UserDto findByEmail(String email) {
        User user = userRepository.findByEmail(email).get();
        return modelMapper.map(user, UserDto.class);
    }

    private void validateUser(User user) {
        User user1 = userRepository.findByEmail(user.getEmail()).orElse(null);
        if(user1 != null){
            throw new ResourceNotFoundException("User with email "+ user.getEmail()+ " already exisits");
        }
    }
}
