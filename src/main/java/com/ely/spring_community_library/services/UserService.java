package com.ely.spring_community_library.services;

import com.ely.spring_community_library.dtos.UserDtos.CreateUserDto;
import com.ely.spring_community_library.dtos.UserDtos.UpdateUserDto;
import com.ely.spring_community_library.entities.User;
import com.ely.spring_community_library.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("all")
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<User> createUser(CreateUserDto createUserDto) {

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

    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    public ResponseEntity<User> getUserById(Long id) {

        Optional<User> userEntity = userRepository.findById(id);

        if (userEntity.isPresent()) {

            return ResponseEntity.ok(userEntity.get());
        } else {

            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<User> updateUserById(Long id, UpdateUserDto updateUserDto) {

        Optional<User> oldUser = userRepository.findById(id);

        if (oldUser.isPresent()) {

            User updatedUser = mergeUserChanges(oldUser.get(), updateUserDto);

            return ResponseEntity.ok(userRepository.save(updatedUser));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private User mergeUserChanges(User oldUser, UpdateUserDto updateUserDto) {

        if (updateUserDto.name() != null) {
            oldUser.setName(updateUserDto.name());
        }
        if (updateUserDto.email() != null) {
            oldUser.setEmail(updateUserDto.email());
        }

        return oldUser;
    }

    public ResponseEntity<Void> deleteUserById(Long id) {

        Optional<User> userToDelete = userRepository.findById(id);

        if (userToDelete.isPresent()) {

            userRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {

            return ResponseEntity.notFound().build();
        }
    }
}
