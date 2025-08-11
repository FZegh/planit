package com.descodeuses.planit.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.descodeuses.planit.dto.UserDTO;
import com.descodeuses.planit.entity.UserEntity;
import com.descodeuses.planit.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    
    @Autowired
    private UserRepository userRepository;

    UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<UserEntity> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    //Conversion vers DTO
    public UserDTO getUserDTOByUsername(String username) {
        UserEntity user = userRepository.findByUsername(username)
            .orElseThrow(() -> new EntityNotFoundException("Utilisateur introuvable : " + username));

        return new UserDTO(user.getId(), user.getUsername(), user.getRole(), user.getFirstname(), user.getLastname());
    }

    public void registerNewUser(String username, String password, String lastname, String firstname, String genre) {

        if (userRepository.existsByUsername(username)) {
        throw new IllegalArgumentException("Ce nom d'utilisateur est déjà pris.");
    }

        UserEntity newUser = new UserEntity();
        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setLastname(lastname);
        newUser.setFirstname(firstname);
        newUser.setGenre(genre);
        newUser.setRole("USER"); // Ajout pour sign-up 

         // Pensez à encoder le mot de passe avec BCryptPasswordEncoder
        userRepository.save(newUser);
    }
}
