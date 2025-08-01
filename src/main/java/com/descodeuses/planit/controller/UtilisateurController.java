package com.descodeuses.planit.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.descodeuses.planit.entity.UtilisateurEntity;
import com.descodeuses.planit.repository.UtilisateurRepository;

import jakarta.persistence.EntityNotFoundException;

@RestController
public class UtilisateurController {

    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurController(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @GetMapping("/api/utilisateur/me")
    public UtilisateurEntity getUtilisateurConnecte() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        return utilisateurRepository.findByUsername(username)
            .orElseThrow(() -> new EntityNotFoundException("Utilisateur non trouvé : " + username));
    }
}
