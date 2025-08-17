package com.descodeuses.planit.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.descodeuses.planit.dto.UserDTO;
import com.descodeuses.planit.entity.UserEntity;
import com.descodeuses.planit.repository.UserRepository;
import com.descodeuses.planit.service.UserService;


import jakarta.persistence.EntityNotFoundException;

@RequestMapping("/api/utilisateur")
@RestController
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;
    

    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }


@GetMapping("/me")
public ResponseEntity<UserDTO> getUtilisateurConnecte() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String username = auth.getName();

    UserEntity entity = userRepository.findByUsername(username)
        .orElseThrow(() -> new EntityNotFoundException("Utilisateur non trouvé : " + username));

    UserDTO dto = new UserDTO(
        entity.getId(),
        entity.getUsername(),
      "ROLE_" +  entity.getRole(),
        entity.getFirstname(),
        entity.getLastname()
    );

    return ResponseEntity.ok(dto);
}

    

     @GetMapping("/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) {
        UserDTO userDto = userService.getUserDTOByUsername(username);
        return ResponseEntity.ok(userDto);
    }


    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        List<UserDTO> users = userService.getAllUserDTOs();
        return ResponseEntity.ok(users);
    }
}
