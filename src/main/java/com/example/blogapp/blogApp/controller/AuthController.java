package com.example.blogapp.blogApp.controller;

import com.example.blogapp.blogApp.dto.LoginRequest;
import com.example.blogapp.blogApp.dto.TokenResponse;
import com.example.blogapp.blogApp.dto.UserDto;
import com.example.blogapp.blogApp.entity.User;
import com.example.blogapp.blogApp.security.JwtService;
import com.example.blogapp.blogApp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtService jwtService;
    private final UserService userService;

    private final AuthenticationManager authenticationManager;
    private final ModelMapper modelMapper;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> generateToken(@RequestBody LoginRequest loginRequest){
        // we need to authenticate
        try {

            // fetch the user from db
            // db user password compare with login mein provided email password
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
            authenticationManager.authenticate(authentication);
            UserDto user = userService.findByEmail(loginRequest.getEmail());


            var tokenReponse = TokenResponse.builder()
                    .accessToken(jwtService.generateAccessToken(user))
                    .refreshToken(jwtService.generateRefreshToken(user))
                    .user(user)
                    .build();

            return new ResponseEntity<>(tokenReponse,HttpStatus.CREATED);
        }catch (AuthenticationException e){
            e.printStackTrace();
            throw new BadCredentialsException("Invalid username and password");
        }
    }
}
