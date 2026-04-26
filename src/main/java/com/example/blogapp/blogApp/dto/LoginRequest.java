package com.example.blogapp.blogApp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequest {


    @NotBlank(message = "Email is required")
    @Email(message = "Invalid Email address")
    private String email;
    @NotBlank(message = "Password is required")
    @Size(min = 3, message = "Min 2 characters required")
    private String password;
}
