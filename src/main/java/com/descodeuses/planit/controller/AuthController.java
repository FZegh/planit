package com.descodeuses.planit.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import com.descodeuses.planit.dto.AuthRequestDTO;
import com.descodeuses.planit.dto.AuthResponseDTO;
import com.descodeuses.planit.security.JwtUtil;
import com.descodeuses.planit.service.LogDocumentService;
import com.descodeuses.planit.service.UtilisateurService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
private UtilisateurService utilisateurService;

@Autowired
private LogDocumentService logDocumentService;


    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO request){
        authenticationManager.authenticate
             (new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword())
            );
        
        String token = jwtUtil.generateToken(request.getUsername());

        this.logDocumentService.addLog("nouvelle tache 2", false, "com.example.demo.DTO.TodoDTO");

            Map<String, String> response = new HashMap<>();
            response.put("message", "Utilisateur créé avec succès");  // Créé la Map

        return ResponseEntity.ok(new AuthResponseDTO(token));

}

@PostMapping("/sign-up")
public ResponseEntity<Map<String, String>> signUp(@RequestBody AuthRequestDTO request) {
    utilisateurService.registerNewUser(request.getUsername(), request.getPassword(), request.getFirstname(), request.getLastname(),request.getGenre());

Map<String, String> response = new HashMap<>();
    response.put("message", "Utilisateur créé avec succès");
    return ResponseEntity.ok(response);
}
    }

