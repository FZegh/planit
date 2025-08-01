package com.descodeuses.planit.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.descodeuses.planit.dto.ProjetDTO;
import com.descodeuses.planit.entity.ProjetEntity;
import com.descodeuses.planit.repository.ProjetRepository;


import jakarta.persistence.EntityNotFoundException;

@Service
public class ProjetService {

    private final ProjetRepository repository;

    public ProjetService(ProjetRepository repository) {
    
        this.repository = repository;
    }

    private ProjetDTO convertToDtO(ProjetEntity projet) {
        return new ProjetDTO(
            projet.getId(),
            projet.getNom(),
            projet.getDueDate());

}

   private ProjetEntity convertToEntity(ProjetDTO projetDTO) {
    ProjetEntity projet = new ProjetEntity();
     projet.setId(projetDTO.getId());
     projet.setNom(projetDTO.getNom());
     projet.setDueDate(projetDTO.getDueDate());

     return projet;
    
    }

    public List<ProjetDTO> getAll() {
        List<ProjetEntity> entities = repository.findAll();

        List<ProjetDTO> dtos = new ArrayList<>();

        for (ProjetEntity item : entities) {
            
            dtos.add(convertToDtO(item));
        }

        return dtos;
}

public ProjetDTO getProjetById(Long id) {

        Optional<ProjetEntity> projet = repository.findById(id);

        if (projet.isEmpty()) {
            throw new EntityNotFoundException("Projet not found with id: " + id);
        }

        return convertToDtO(projet.get());
}

public ProjetDTO create(ProjetDTO dto) {
       
        ProjetEntity entity = convertToEntity(dto);

        
        ProjetEntity savedEntity = repository.save(entity);

    
        return convertToDtO(savedEntity);
    }

    public ProjetDTO update(Long id, ProjetDTO dto) {
        ProjetEntity projetEntity = repository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Projet non trouvée avec l'id: " + id));

            projetEntity.setNom(dto.getNom());
            //projetEntity.setDueDate(dto.getDueDate());

            ProjetEntity updatedProjetEntity = repository.save(projetEntity);

        // Conversion en DTO pour la réponse
        return convertToDtO(updatedProjetEntity);

}

 public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Projet non trouvée avec l'id: " + id);
        }
        repository.deleteById(id);
    }

}