package com.descodeuses.planit.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.descodeuses.planit.dto.AuthRequestDTO;
import com.descodeuses.planit.dto.AuthResponseDTO;
import com.descodeuses.planit.dto.UserDTO;
import com.descodeuses.planit.entity.UserEntity;
import com.descodeuses.planit.repository.UserRepository;
import com.descodeuses.planit.security.JwtUtil;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UtilisateurService {

    private final PasswordEncoder passwordEncoder;
    
    @Autowired
    private UserRepository userRepository;

     @Autowired
    private AuthenticationManager authenticationManager;

     @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private LogDocumentService logDocumentService;


    UtilisateurService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    public Optional<UserEntity> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    //Conversion vers DTO
    public UserDTO getUserDTOByUsername(String username) {
        UserEntity user = userRepository.findByUsername(username)
            .orElseThrow(() -> new EntityNotFoundException("Utilisateur introuvable : " + username));

        return new UserDTO(user.getId(), user.getUsername(), "ROLE_" + user.getRole(), user.getFirstname(), user.getLastname());
    }

    public void registerNewUser(String username, String password, String lastname, String firstname, String genre) {
        UserEntity newUser = new UserEntity();
        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setLastname(lastname);
        newUser.setFirstname(firstname);
        newUser.setGenre(genre);
        newUser.setRole("USER"); 
         // Pensez à encoder le mot de passe avec BCryptPasswordEncoder
        userRepository.save(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO request){
        authenticationManager.authenticate
             (new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword())
            );

    UserEntity user = userRepository.findByUsername(request.getUsername())
        .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));
        
        String token = jwtUtil.generateToken(request.getUsername(),"ROLE_" + user.getRole());

        this.logDocumentService.addLog("nouvelle tache 2", false, "com.example.demo.DTO.TodoDTO");

        System.out.println("Rôle récupéré depuis la base : " + user.getRole());


        return ResponseEntity.ok(new AuthResponseDTO(token,"ROLE_" + user.getRole())); //

}
public List<UserDTO> getAllUserDTOs()
{
        return userRepository.findAll().stream()
        .map(user -> new UserDTO(
            user.getId(),
            user.getUsername(),
             "ROLE_" + user.getRole(),
            user.getFirstname(),
            user.getLastname()

        ))
        .toList();
    }

}
