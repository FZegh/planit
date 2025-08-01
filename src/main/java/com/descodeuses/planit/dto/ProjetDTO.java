package com.descodeuses.planit.dto;

import java.time.LocalDate;

public class ProjetDTO {

    private Long id;
    private String nom;
    private LocalDate dueDate;

     public ProjetDTO() { // sérialisation/désérialisation JSON
        }

    public ProjetDTO(Long id, String nom, LocalDate dueDate){
        this.id = id;
        this.nom = nom;
        this.dueDate = dueDate;


    }

     public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
       this.dueDate = dueDate;
    }


}
