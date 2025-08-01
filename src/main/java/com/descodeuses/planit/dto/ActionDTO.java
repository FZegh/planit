package com.descodeuses.planit.dto;

import java.time.LocalDate;

// Fichier intermediaire où l'on met uniquement les données non sensible ( on retire ex Private Password)
public class ActionDTO {

    private Long id;
    private String title;
    private boolean completed;
    private LocalDate dueDate;
    private Integer priority;
    private String description;
    private Long projetId;
    private Long utilisateurId;

   
    
    public ActionDTO() {}

    public ActionDTO(Long id, String title,  boolean completed, LocalDate dueDate, Integer priority,  String description, Long projetId, Long utilisateurId) {
        this.id = id;
        this.title = title;
        this.completed = completed;
        this.dueDate = dueDate;
        this.priority = priority;
        this.description = description;
        this.projetId = projetId;
        this.utilisateurId = utilisateurId;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public boolean isCompleted() {
        return completed;
    }

    public boolean getCompleted() {
       return this.completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }


    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getProjetId(){
        return projetId;
    }

    public void setProjetId(Long projetId){
        this.projetId = projetId;
    }

     public Long getUtilisateurId() {
        return this.utilisateurId;
    }

    public void setUtilisateurId(Long utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

}



