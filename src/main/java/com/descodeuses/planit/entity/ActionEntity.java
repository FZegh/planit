package com.descodeuses.planit.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity  // model represente la table de notre classe Java
@Table(name="todo")
public class ActionEntity {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@Column(nullable = false)
private String title;

private Boolean completed;

private LocalDate dueDate;

private Integer priority;

private String description;

@ManyToOne      
@JoinColumn(name = "projet_id")
private ProjetEntity projet;

@ManyToOne
@JoinColumn(name = "contact_id", referencedColumnName = "id")
private ContactEntity contact;

@ManyToOne    //Many correspond aux actions et One à l'utilisateur
@JoinColumn(name = "user_id")
private UserEntity user;

   

@ManyToMany
@JoinTable(
    name = "todo_contact", // tu peux renommer proprement (évite "todo-contact")
    joinColumns = @JoinColumn(name = "todo_id"),
    inverseJoinColumns = @JoinColumn(name = "members_id", referencedColumnName = "id")

)

private Set<ContactEntity> members = new HashSet<>();

    


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle(){        
        return title;
    }
    
    public void setTitle(String title){    
        this.title = title;
    }

    
    public Boolean isCompleted() {return completed;}

    public boolean getCompleted() {
        return this.completed;
    }


    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Integer getPriority() {
        return this.priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProjetEntity getProjet() {
        return projet;
    }

    public void setProjet(ProjetEntity projet) {
        this.projet = projet;
    }
public Set<ContactEntity> getMembers() {
    return members;
}

public void setMembers(Set<ContactEntity> members) {
    this.members = members;
}

 public ContactEntity getContact() {
        return this.contact;
    }

    public void setContact(ContactEntity contact) {
        this.contact = contact;
    }

public UserEntity getUser() {
        return this.user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
    
}

