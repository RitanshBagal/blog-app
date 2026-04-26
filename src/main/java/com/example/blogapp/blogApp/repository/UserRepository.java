package com.example.blogapp.blogApp.repository;

import com.example.blogapp.blogApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//all CRUD functionality
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}