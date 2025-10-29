package com.ely.spring_community_library.controllers;

import com.ely.spring_community_library.dtos.AuthDtos.LoginResponseDto;
import com.ely.spring_community_library.dtos.UserDtos.CreateUserDto;
import com.ely.spring_community_library.dtos.AuthDtos.LoginDto;
import com.ely.spring_community_library.entities.User;
import com.ely.spring_community_library.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    private ResponseEntity<User> register(@RequestBody @Valid CreateUserDto createUserDto) {

        return authService.register(createUserDto);
    }

    @PostMapping("/login")
    private ResponseEntity<LoginResponseDto> login(@RequestBody @Valid LoginDto loginDto) {

        return authService.login(loginDto);
    }
}
