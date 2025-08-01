package com.descodeuses.planit.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.descodeuses.planit.dto.ContactDTO;
import com.descodeuses.planit.service.ContactService;

import org.springframework.web.bind.annotation.PostMapping;



@RestController     //annotation enrichi des classes ou methode existante. Le contrôleur fournit un point d'entrée centralisé qui contrôle et gère le traitement des requêtes Web 
@RequestMapping("/api/contact")    //similaire  au chemin angular
public class ContactController { //pour acceder taper apres localhost8080 puis le chemin /api/action/

  private final ContactService service;

  public ContactController(ContactService service) {
        this.service = service;
    }

    @GetMapping("/hello")            //methode get similaire a angular Crud .get
    public String HelloContact() {

      ArrayList<Integer> liste = new ArrayList<Integer>();   //ArrayList : méthode pour créer tableau ; Integer : précise le type du tableau
        liste.add(1);
        liste.add(2);  //coomme .push sur java
        liste.add(3);
        
        return "Hello World !";

    }

    @GetMapping("/{id}")
      public ResponseEntity<ContactDTO> getById(@PathVariable Long id) {
        ContactDTO contact = service.getContactById(id);
        contact.setNom("Peishin");
         return new ResponseEntity<>(contact, HttpStatus.OK);      


}

 @GetMapping
    public ResponseEntity<List<ContactDTO>> getAll() {

       /*Contact contact1 = new Contact();
       contact1.setName("Peishin");

       Contact contact2 = new Contact();
       contact2.setName("Marcel");

       ArrayList<Contact> list = new ArrayList<>();
       list.add(contact1);
       list.add(contact2);*/


         List<ContactDTO> items = service.getAll();
        return new ResponseEntity<>(items, HttpStatus.OK);

        
    }



@PostMapping
public String createContact() {
  
  return "create";
}

@PatchMapping
public String updateContact() {
  
  return "update";
}

 @DeleteMapping
    public String deleteContact() {
        return "delete";
    }


}

