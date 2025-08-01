package com.descodeuses.planit.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.descodeuses.planit.dto.ActionDTO;
import com.descodeuses.planit.service.ActionService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController // annotation enrichi des classes ou methode existante. Le contrôleur fournit un
                // point d'entrée centralisé qui contrôle et gère le traitement des requêtes Web
@RequestMapping("/api/action") // similaire au chemin angular
public class ActionController { // pour acceder taper apres localhost8080 puis le chemin /api/action/

  private final ActionService service;

  // La dependency injection (DI)
  // tu ne crées pas toi-même les objets dont une classe a besoin
  // On te les injecte de l’extérieur

  // Constructeur
  // Recoit le service
  // Comme c'est un service donc il est injectable

  // Tous les injectable sont recu par principe
  // depuis le constructeur

  // Quand tu injectes par constructeur
  // les dépendances sont obligatoires dès le départ
  // donc le code est plus sûr
  public ActionController(ActionService service) {
    this.service = service;
  }

  @GetMapping("/hello") // methode get similaire a angular Crud .get
  public ResponseEntity<String> HelloAction() {

    ArrayList<Integer> liste = new ArrayList<Integer>(); // ArrayList : méthode pour créer tableau ; Integer : précise
                                                         // le type du tableau
    liste.add(1);
    liste.add(2); // coomme .push sur java
    liste.add(3);

    return new ResponseEntity<>("Hello World !", HttpStatus.OK);

  }

  @GetMapping("/{id}")
  public ResponseEntity<ActionDTO> getActionById(@PathVariable Long id) {

    ActionDTO action = service.getActionById(id);

    return new ResponseEntity<ActionDTO>(action, HttpStatus.OK);
  }

  /*
   * {
   * Action action = new Action();
   * action.setTitle("Enovyer un mail");
   * return new ResponseEntity<>(action, HttpStatus.OK);
   * 
   * }
   */

  @GetMapping
  public ResponseEntity<List<ActionDTO>> getAll() { // bien mettre <> sur chaque "methode"

    /*
     * Action action1 = new Action();
     * action1.setTitle("Enovoyer un mail");
     * 
     * Action action2 = new Action();
     * action2.setTitle("Appel telephoniquel");
     * 
     * ArrayList<Action> list = new ArrayList<>();
     * list.add(action1);
     * list.add(action2);
     */

    List<ActionDTO> items = service.getAll();
    return new ResponseEntity<>(items, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<ActionDTO> create(@RequestBody ActionDTO requestDTO) {
    ActionDTO createdDTO = service.create(requestDTO);
    return new ResponseEntity<>(createdDTO, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ActionDTO> update(@PathVariable Long id, @RequestBody ActionDTO requestDTO) {

    ActionDTO updatedDTO = service.update(id, requestDTO);
    return new ResponseEntity<>(updatedDTO, HttpStatus.OK);

  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteAction(@PathVariable Long id) {
    service.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 : supprimé avec succès, sans contenu à retourner
  }

}
