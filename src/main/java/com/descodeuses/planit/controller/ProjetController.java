package com.descodeuses.planit.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.descodeuses.planit.dto.ProjetDTO;
import com.descodeuses.planit.service.ProjetService;

@RestController
@RequestMapping("/api/projet")
public class ProjetController {

    private final ProjetService service;

    public ProjetController(ProjetService service) {
    this.service = service;
  }

  @GetMapping("/hello") // methode get similaire a angular Crud .get
  public ResponseEntity<String> HelloProjet() {

    ArrayList<Integer> liste = new ArrayList<Integer>(); // ArrayList : méthode pour créer tableau ; Integer : précise
                                                         // le type du tableau
    liste.add(1);
    liste.add(2); // coomme .push sur java
    liste.add(3);

    return new ResponseEntity<>("Hello World !", HttpStatus.OK);

  }

  @GetMapping("/{id}")
  public ResponseEntity<ProjetDTO> getProjetById(@PathVariable Long id) {

    ProjetDTO projet = service.getProjetById(id);

    return new ResponseEntity<ProjetDTO>(projet, HttpStatus.OK);
  }


  @GetMapping
  public ResponseEntity<List<ProjetDTO>> getAll() { 

    
    List<ProjetDTO> items = service.getAll();
    return new ResponseEntity<>(items, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<ProjetDTO> create(@RequestBody ProjetDTO requestDTO) {
    ProjetDTO createdDTO = service.create(requestDTO);
    return new ResponseEntity<>(createdDTO, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProjetDTO> update(@PathVariable Long id, @RequestBody ProjetDTO requestDTO) {

    ProjetDTO updatedDTO = service.update(id, requestDTO);
    return new ResponseEntity<>(updatedDTO, HttpStatus.OK);

  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProjet(@PathVariable Long id) {
    service.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}


