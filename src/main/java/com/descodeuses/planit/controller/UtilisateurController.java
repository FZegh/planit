package com.descodeuses.planit.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.descodeuses.planit.dto.UtilisateurDTO;
import com.descodeuses.planit.entity.UtilisateurEntity;
import com.descodeuses.planit.repository.UtilisateurRepository;
import com.descodeuses.planit.service.UtilisateurService;


import jakarta.persistence.EntityNotFoundException;

@RequestMapping("/api/utilisateur")
@RestController
public class UtilisateurController {

    private final UtilisateurRepository utilisateurRepository;
    private final UtilisateurService utilisateurService;
    

    public UtilisateurController(UtilisateurRepository utilisateurRepository, UtilisateurService utilisateurService) {
        this.utilisateurRepository = utilisateurRepository;
        this.utilisateurService = utilisateurService;
    }


@GetMapping("/me")
public ResponseEntity<UtilisateurDTO> getUtilisateurConnecte() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String username = auth.getName();

    UtilisateurEntity entity = utilisateurRepository.findByUsername(username)
        .orElseThrow(() -> new EntityNotFoundException("Utilisateur non trouvé : " + username));

    UtilisateurDTO dto = new UtilisateurDTO(
        entity.getId(),
        entity.getUsername(),
        entity.getRole(),
        entity.getFirstname(),
        entity.getLastname()
    );

    return ResponseEntity.ok(dto);
}

    

     @GetMapping("/{username}")
    public ResponseEntity<UtilisateurDTO> getUserByUsername(@PathVariable String username) {
        UtilisateurDTO userDto = utilisateurService.getUserDTOByUsername(username);
        return ResponseEntity.ok(userDto);
    }
}
