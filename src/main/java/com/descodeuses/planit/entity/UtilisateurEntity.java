package com.descodeuses.planit.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "utilisateur")
public class UtilisateurEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;
    private String password;
    private String role;
    
    @Column(name = "prenom")
private String prenom;

   

    public List<ActionEntity> getTodos() {
        return this.todos;
    }

    public void setTodos(List<ActionEntity> todos) {
        this.todos = todos;
    }

    @OneToMany(mappedBy = "utilisateur")
    private List<ActionEntity> todos = new ArrayList<>();

    public UtilisateurEntity() {
    }

    public UtilisateurEntity(String username, String password, String role, String prenom) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.prenom =prenom;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
