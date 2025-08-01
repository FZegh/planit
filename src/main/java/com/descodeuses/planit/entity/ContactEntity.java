package com.descodeuses.planit.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "contact")
public class ContactEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String prenom;

    private String nom;
    private String email;
    private String telephone;
    private LocalDate date;

 @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL)
private Set<ActionEntity> todos = new HashSet<>();

@ManyToOne
@JoinColumn(name = "projet_id")
private ProjetEntity projet;

@ManyToMany(mappedBy = "members")
private Set<ActionEntity> actionsAsMember = new HashSet<>();


public ProjetEntity getProjet() { return projet; }
public void setProjet(ProjetEntity projet) { this.projet = projet; }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public Set<ActionEntity> getTodos() { return todos; }
    public void setTodos(Set<ActionEntity> todos) { this.todos = todos; }

    public Set<ActionEntity> getActionsAsMember() {
    return actionsAsMember;
}
    public void setActionsAsMember(Set<ActionEntity> actionsAsMember) {
    this.actionsAsMember = actionsAsMember;
}

}

