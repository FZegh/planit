package com.descodeuses.planit.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;

@Entity
@Table(name = "utilisateur")  //attention ne pas modifier le nom de la table
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private String genre;
    
    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Column(name = "firstname", nullable = false)
    private String firstname;

   
    public UserEntity() {
    }

    public UserEntity(String username, String password, String role, String lastname, String firstname, String genre) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.lastname = lastname;
        this.firstname = firstname;
        this.genre = genre;
        


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
    

    public String getFirstname(){
        return firstname;
    }

    public void setFirstname(String firstname){
         this.firstname = firstname;
    }

     public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getGenre() {
    return genre;
}

public void setGenre(String genre) {
    this.genre = genre;
}

}
