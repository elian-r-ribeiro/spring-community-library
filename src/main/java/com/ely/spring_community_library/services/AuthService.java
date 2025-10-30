package com.ely.spring_community_library.services;

import com.ely.spring_community_library.dtos.AuthDtos.LoginResponseDto;
import com.ely.spring_community_library.dtos.UserDtos.CreateUserDto;
import com.ely.spring_community_library.dtos.AuthDtos.LoginDto;
import com.ely.spring_community_library.entities.User;
import com.ely.spring_community_library.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    @Lazy
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public ResponseEntity<User> register(CreateUserDto createUserDto) {

        String encryptedPassword = passwordEncoder.encode(createUserDto.password());

        System.out.println(encryptedPassword);

        final User newUser = new User(
                null,
                createUserDto.name(),
                createUserDto.email(),
                encryptedPassword,
                null,
                createUserDto.role(),
                createUserDto.active()
        );

        return ResponseEntity.ok(userRepository.save(newUser));
    }

    public ResponseEntity<LoginResponseDto> login(LoginDto loginDto) {

        final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                loginDto.email(),
                loginDto.password()
        );
        final Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        final String token = tokenService.generateToken((User) authentication.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDto(token));
    }
}
