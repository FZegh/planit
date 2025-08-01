package com.descodeuses.planit.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;

@Entity
@Table(name= "projet")
public class ProjetEntity {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@OneToMany(mappedBy = "projet", cascade = CascadeType.ALL)
private List<ActionEntity> actions = new ArrayList<>();

@OneToMany(mappedBy = "projet", cascade = CascadeType.ALL, orphanRemoval = true)
private List<ContactEntity> contacts = new ArrayList<>();


@Column(nullable = false)
private String nom;
private LocalDate dueDate;

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

    public List<ActionEntity> getActions() {
    return actions;
}

public void setActions(List<ActionEntity> actions) {
    this.actions = actions;
}
public List<ContactEntity> getContact() { return contacts; }

public void setContact(List<ContactEntity> contacts) { this.contacts = contacts; }



}
