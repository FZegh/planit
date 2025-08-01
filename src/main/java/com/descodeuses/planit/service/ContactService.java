package com.descodeuses.planit.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;


import com.descodeuses.planit.dto.ContactDTO;
import com.descodeuses.planit.entity.ContactEntity;
import com.descodeuses.planit.repository.ContactRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ContactService {

    private final ContactRepository repository;

    public ContactService(ContactRepository repository){
        this.repository = repository;
    }
    
    
    private ContactDTO convertToDTO(ContactEntity contact) {

        return new ContactDTO (
            contact.getId(),
            contact.getPrenom(),
            contact.getNom(),
            contact.getEmail(),
            contact.getDate());
    }

    private ContactEntity convertToEntity(ContactDTO contactDTO) {

        ContactEntity contact = new ContactEntity();
        contact.setId(contactDTO.getId());
        contact.setPrenom(contactDTO.getPrenom());
        contact.setNom(contactDTO.getNom());
        contact.setEmail(contactDTO.getEmail());
        contact.setDate(contactDTO.getDate());

        return contact;

    }

      public List<ContactDTO> getAll() {
        List<ContactEntity> entities = repository.findAll();     
        //Declarer une variable liste de action DTO
        List<ContactDTO> dtos = new ArrayList<>(); 

        for (ContactEntity item : entities) {

            dtos.add (convertToDTO(item));
        }
      return dtos;

}

public ContactDTO getContactById(Long id) {

    Optional<ContactEntity> contact = repository.findById(id);

    if(contact.isEmpty()){

        throw new EntityNotFoundException("Contact not found with id: " + id);
    }

    return convertToDTO(contact.get());


}
public ContactDTO createContact(ContactDTO contactDTO) {
    ContactEntity contact = convertToEntity(contactDTO);
    ContactEntity saved = repository.save(contact);
    return convertToDTO(saved);
}

}

