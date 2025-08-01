package com.descodeuses.planit.dto;


import java.time.LocalDate;

public class ContactDTO {

    private Long id;
    private String prenom;
    private String nom;
    private String email;

    private LocalDate date;
    public ContactDTO() {}

    public ContactDTO(Long id, String prenom, String nom, String email , LocalDate date) {

        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
        this.date = date;
    
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getNom(){
        return nom;
    }

    public void setNom(String nom){
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }


    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }
    public LocalDate getDate() {
        return date;
    }


    public void setdate(LocalDate date) {
        this.date = date;
    }


    
}







