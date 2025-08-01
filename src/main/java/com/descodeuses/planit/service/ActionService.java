package com.descodeuses.planit.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.Authentication; // ✅ Bon import pour Spring Security

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.descodeuses.planit.dto.ActionDTO;
import com.descodeuses.planit.entity.ActionEntity;
import com.descodeuses.planit.entity.ProjetEntity;
import com.descodeuses.planit.entity.UtilisateurEntity;
import com.descodeuses.planit.repository.ActionRepository;
import com.descodeuses.planit.repository.ProjetRepository;
import com.descodeuses.planit.repository.UtilisateurRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ActionService {

    private final ActionRepository repository;
    private final ProjetRepository projetRepository;
    private final UtilisateurRepository utilisateurRepository;

    public ActionService(ActionRepository repository, ProjetRepository projetRepository,
            UtilisateurRepository utilisateurRepository) {
        this.repository = repository;
        this.projetRepository = projetRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    private ActionDTO convertToDtO(ActionEntity action) {
        return new ActionDTO(
                action.getId(),
                action.getTitle(),
                action.getCompleted(),
                action.getDueDate(),
                action.getPriority(),
                action.getDescription(),
                action.getProjet() != null ? action.getProjet().getId() : null,
                action.getUtilisateur() != null ? action.getUtilisateur().getId() : null);

    }

    private ActionEntity convertToEntity(ActionDTO actionDTO) {
        ActionEntity action = new ActionEntity();
        action.setId(actionDTO.getId());
        action.setTitle(actionDTO.getTitle());
        action.setCompleted(actionDTO.getCompleted());
        action.setDueDate(actionDTO.getDueDate());
        action.setPriority(actionDTO.getPriority());
        action.setDescription(actionDTO.getDescription());

        if (actionDTO.getProjetId() != null) {
            ProjetEntity projet = projetRepository.findById(actionDTO.getProjetId())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Projet non trouvé avec l'id: " + actionDTO.getProjetId()));
            action.setProjet(projet);
        }

        

        return action;

    }

    public List<ActionDTO> getAll() {
        List<ActionEntity> entities = repository.findAll(); // Appartient à l'import de JPARepository (Spring Boot :
                                                            // voir
        // fichier Repository)
        // Declarer une variable liste de action DTO
        List<ActionDTO> dtos = new ArrayList<>();

        // Faire boucle sur la liste action DTO
        for (ActionEntity item : entities) {
            // Convertir action vers action dto
            // Ajouter action dto dans la liste
            dtos.add(convertToDtO(item));
        }

        return dtos;

    }

    public ActionDTO getActionById(Long id) {
        // Action action =
        // repository
        // .findById(id)
        // .orElseThrow() -> new EntityNotFoundException("Action not found with id:" +
        // id);

        // Version longue explicite
        Optional<ActionEntity> action = repository.findById(id);

        if (action.isEmpty()) {
            throw new EntityNotFoundException("Action not found with id: " + id);
        }

        return convertToDtO(action.get());
    }

    // return convertToDtO(action);
    public ActionDTO create(ActionDTO dto) {
        // Convertir le DTO en entité
        // entité = convertirVersEntité(dto)
        ActionEntity entity = convertToEntity(dto);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        UtilisateurEntity utilisateurConnecte = utilisateurRepository
        .findByUsername(username)
        .orElseThrow(() -> new EntityNotFoundException(
            "Utilisateur connecté non trouvé avec le username: " + username));

            entity.setUtilisateur(utilisateurConnecte);


        // Sauvegarder l'entité dans la base de données
        // entitéEnregistrée = référentiel.sauvegarder(entité)
        ActionEntity savedEntity = repository.save(entity);

        // Convertir l'entité enregistrée en DTO et retourner
        // retourner convertirVersDTO(entitéEnregistrée)
        return convertToDtO(savedEntity);
    }

    public ActionDTO update(Long id, ActionDTO dto) {
        ActionEntity ActionEntity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Action non trouvée avec l'id: " + id));

        // Mise à jour des champs de l'entité
        ActionEntity.setTitle(dto.getTitle());
        ActionEntity.setCompleted(dto.getCompleted());
        ActionEntity.setDueDate(dto.getDueDate());
        ActionEntity.setPriority(dto.getPriority());
        ActionEntity.setDescription(dto.getDescription());

        if (dto.getProjetId() != null) {
            ProjetEntity projet = projetRepository.findById(dto.getProjetId())
                    .orElseThrow(
                            () -> new EntityNotFoundException("Projet non trouvé avec l'id: " + dto.getProjetId()));
            ActionEntity.setProjet(projet);
        }

        // Sauvegarde de l'entité mise à jour
        ActionEntity updatedAction = repository.save(ActionEntity);

        // Conversion en DTO pour la réponse
        return convertToDtO(updatedAction);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Action non trouvée avec l'id: " + id);
        }
        repository.deleteById(id);
    }

}
